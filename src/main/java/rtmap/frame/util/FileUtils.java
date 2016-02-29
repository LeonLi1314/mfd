package rtmap.frame.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作帮助类
 * 
 * @author liqingshan
 *
 */
public class FileUtils {
	/**
	 * 校验文件是否存在
	 *
	 * @param filePath
	 *            文件路径
	 * @return 如果文件路径件不存在，则为真
	 */
	public static boolean isExists(String filePath) {
		if (StringUtils.isNullOrEmpty(filePath))
			return false;

		return new File(filePath).exists();
	}

	/**
	 * 创建文件
	 *
	 * @param filePath
	 *            文件路径
	 * @return 是否成功
	 */
	public static boolean createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			return true;

		File parentFile = file.getParentFile();
		if (parentFile != null) {
			if (!createDir(parentFile)) {
				return false;
			}
		}

		try {
			return file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 递归创建文件目录
	 *
	 * @param dir
	 *            目录
	 * @return 是否成功
	 */
	public static boolean createDir(File dir) {
		if (dir.exists())
			return true;

		File parentDir = dir.getParentFile();
		if (parentDir.exists())
			return true;

		if (!createDir(parentDir))
			return false;

		return dir.mkdir();
	}

	/**
	 * 删除文件
	 *
	 * @param filePath
	 *            文件路径
	 */
	public static void delete(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return;

		if (file.isFile()) {
			if (!file.delete())
				throw new RuntimeException("删除文件：" + filePath + "失败");

			return;
		}

		File[] files = file.listFiles();
		if (files != null && files.length > 0) {
			assert files != null;
			for (File f : files) {
				delete(f.getAbsolutePath());
			}
		}

		if (!file.delete()) {
			throw new RuntimeException("删除目录：" + filePath + "失败");
		}
	}

	/**
	 * 删除目录文件
	 *
	 * @param dir
	 *            目录路径
	 * @param suffix
	 *            删除文件后缀集合。为空则不删除
	 */
	public static void deleteFilesOfDir(String dir, final String... suffix) {
		if (StringUtils.isNullOrEmpty(dir) || suffix == null || suffix.length == 0 || !isExists(dir))
			return;

		File[] files = new File(dir).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				for (String s : suffix) {
					if (name.endsWith(s)) {
						return true;
					}
				}
				return false;
			}
		});

		if (files != null && files.length > 0) {
			for (File file : files) {
				delete(file.getPath());
			}
		}
	}

	/**
	 * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
	 */
	public static void readFileByBytes(String filePath) {
		File file = new File(filePath);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读一个字节：");
			// 一次读一个字节
			in = new FileInputStream(file);
			int tempbyte;
			while ((tempbyte = in.read()) != -1) {
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		try {
			System.out.println("以字节为单位读取文件内容，一次读多个字节：");
			// 一次读多个字节
			byte[] tempbytes = new byte[100];
			int byteread = 0;
			in = new FileInputStream(filePath);
			showAvailableBytes(in);
			// 读入多个字节到字节数组中，byteread为一次读入的字节数
			while ((byteread = in.read(tempbytes)) != -1) {
				System.out.write(tempbytes, 0, byteread);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以字符为单位读取文件，常用于读文本，数字等类型的文件
	 */
	public static void readFileByChars(String filePath) {
		File file = new File(filePath);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(filePath));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length) && (tempchars[tempchars.length - 1] != '\r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static void readFileByLines(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 随机读取文件内容
	 */
	public static void readFileByRandomAccess(String filePath) {
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容：");
			// 打开一个随机访问文件流，按只读方式
			randomFile = new RandomAccessFile(filePath, "r");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 读文件的起始位置
			int beginIndex = (fileLength > 4) ? 4 : 0;
			// 将读文件的开始位置移到beginIndex位置。
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
			// 将一次读取的字节数赋给byteread
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 从文件中读取内容
	 *
	 * @param filePath
	 *            文件路径
	 * @return 内容
	 */
	public static String readFileByBufferedReader(String filePath) {
		BufferedReader reader = null;
		try {
			StringBuilder content = new StringBuilder();
			File file = new File(filePath);
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
			return content.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * 显示输入流中还剩的字节数
	 */
	public static void showAvailableBytes(InputStream in) {
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A方法追加文件：使用RandomAccessFile
	 */
	public static void appendMethodA(String filePath, String content) {
		try {
			RandomAccessFile randomFile = new RandomAccessFile(filePath, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * B方法追加文件：使用FileWriter
	 */
	public static void appendMethodB(String filePath, String content) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(filePath, true);
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A方法覆盖文件：使用FileWriter
	 */
	public static void overrideFileMethodA(String filePath, String content) {
		FileWriter writer = null;

		try {
			writer = new FileWriter(filePath);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * B方法覆盖文件：使用BufferedOutputStream
	 */
	public static void overrideFileMethodB(String filePath, String content) {
		FileOutputStream outStr = null;
		BufferedOutputStream buff = null;

		try {
			outStr = new FileOutputStream(new File(filePath));
			buff = new BufferedOutputStream(outStr);

			buff.write(content.getBytes());
			buff.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outStr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static int countFiles = 0;// 声明统计文件个数的变量
	static int countFolders = 0;// 声明统计文件夹的变量

	/**
	 *  递归查找指定目录下包含关键字的文件
	 */
	public static File[] searchFile(File folder, final String keyWord) {
		File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
			@Override
			public boolean accept(File pathname) {// 实现FileFilter类的accept方法
				if (pathname.isFile())// 如果是文件
					countFiles++;
				else
					// 如果是目录
					countFolders++;
				if (pathname.isDirectory()
						|| (pathname.isFile() && pathname.getName().toLowerCase().contains(keyWord.toLowerCase())))// 目录或文件包含关键字
					return true;
				return false;
			}
		});

		List<File> result = new ArrayList<File>();// 声明一个集合
		for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
			if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
				result.add(subFolders[i]);
			} else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
				File[] foldResult = searchFile(subFolders[i], keyWord);
				for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
					result.add(foldResult[j]);// 文件保存到集合中
				}
			}
		}

		File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
		result.toArray(files);// 集合数组化
		return files;
	}
}
