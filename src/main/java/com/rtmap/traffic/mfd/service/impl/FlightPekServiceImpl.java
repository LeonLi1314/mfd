package com.rtmap.traffic.mfd.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.cache.FltCache;
import com.rtmap.traffic.mfd.dao.IFltArrfBeltPekDao;
import com.rtmap.traffic.mfd.dao.IFltArrfPekDao;
import com.rtmap.traffic.mfd.dao.IFltChangeinfoPekDao;
import com.rtmap.traffic.mfd.dao.IFltDepfCounterPekDao;
import com.rtmap.traffic.mfd.dao.IFltDepfGatePekDao;
import com.rtmap.traffic.mfd.dao.IFltDepfPekDao;
import com.rtmap.traffic.mfd.domain.ArrdepFlag;
import com.rtmap.traffic.mfd.domain.FltChangeinfoFiledsConst;
import com.rtmap.traffic.mfd.domain.FltTypeConst;
import com.rtmap.traffic.mfd.domain.PageRst;
import com.rtmap.traffic.mfd.domain.cond.ArrdepPlaceCond;
import com.rtmap.traffic.mfd.domain.cond.FltIdCond;
import com.rtmap.traffic.mfd.domain.cond.FltNoCond;
import com.rtmap.traffic.mfd.domain.cond.PageCond;
import com.rtmap.traffic.mfd.domain.cond.ShakeCond;
import com.rtmap.traffic.mfd.domain.cond.SubscriberCond;
import com.rtmap.traffic.mfd.domain.dto.FltDetailDto;
import com.rtmap.traffic.mfd.domain.dto.FltInfoDto;
import com.rtmap.traffic.mfd.domain.entity.Airport;
import com.rtmap.traffic.mfd.domain.entity.ArrfBeltPek;
import com.rtmap.traffic.mfd.domain.entity.ArrfPek;
import com.rtmap.traffic.mfd.domain.entity.DepfCounterPek;
import com.rtmap.traffic.mfd.domain.entity.DepfGatePek;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;
import com.rtmap.traffic.mfd.domain.entity.FltChangeinfoPek;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;
import com.rtmap.traffic.mfd.service.IBasService;
import com.rtmap.traffic.mfd.service.IFlightService;
import com.rtmap.traffic.mfd.service.ISubscribeService;

import rtmap.frame.util.DatePatterns;
import rtmap.frame.util.DateUtils;
import rtmap.frame.util.JaxbUtils;
import rtmap.frame.util.StringUtils;

/**
 * 航班动态服务层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Service("pekService")
public class FlightPekServiceImpl implements IFlightService {
	/**
	 * 日志记录器
	 */
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private IFltArrfPekDao fltArrfPekDao;
	@Resource
	private IFltDepfPekDao fltDepfPekDao;
	@Resource
	private IBasService basService;
	@Resource
	private ISubscribeService subscribeService;
	@Resource
	private IFltChangeinfoPekDao fltChangeinfoPekDao;
	@Resource
	private IFltArrfBeltPekDao beltPekDao;
	@Resource
	private IFltDepfCounterPekDao counterPekDao;
	@Resource
	private IFltDepfGatePekDao gatePekDao;
	private String currentAirportCn = "北京";
	private List<String> arrfIgnoredFields;
	private List<String> depfIgnoredFields;

	{
		arrfIgnoredFields = new ArrayList<>();
		arrfIgnoredFields.add("belts");
		arrfIgnoredFields.add("createTime");
		arrfIgnoredFields.add("updateTime");

		depfIgnoredFields = new ArrayList<>();
		depfIgnoredFields.add("gates");
		depfIgnoredFields.add("counters");
		depfIgnoredFields.add("createTime");
		depfIgnoredFields.add("updateTime");
	}

	@Override
	public String test() {
		fltArrfPekDao.selectByArrfId("test");
		System.out.println("测试成功！");

		return "测试成功！";
	}

	@Override
	public PageRst<FltInfoDto> getFlightsByFltNoCond(PageCond<FltNoCond> pageCond) {
		List<FltInfoDto> rst = new ArrayList<>();
		PageRst<FltInfoDto> pageRst = new PageRst<>();
		pageRst.setFirstPage(pageCond.getPageNo() == 1);
		pageRst.setRst(rst);
		int totalCount = 0;

		if (ArrdepFlag.A == pageCond.getCond().getArrdep()) {
			totalCount = fltArrfPekDao.selectTotalCountByFltNoCond(pageCond.getCond());
			List<ArrfPek> arrfList = fltArrfPekDao.selectByFltNoCond(pageCond.getPageNo(), pageCond.getPageSize(),
					pageCond.getCond());

			if (arrfList == null || arrfList.size() == 0)
				return pageRst;

			for (ArrfPek arrf : arrfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByArrfPek(arrf, fltInfo);
				rst.add(fltInfo);
			}
		} else {
			totalCount = fltDepfPekDao.selectTotalCountByFltNoCond(pageCond.getCond());
			List<DepfPek> depfList = fltDepfPekDao.selectByFltNoCond(pageCond.getPageNo(), pageCond.getPageSize(),
					pageCond.getCond());

			for (DepfPek depf : depfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByDepfPek(depf, fltInfo);
				rst.add(fltInfo);
			}
		}

		pageRst.setTotalCount(totalCount);
		int remainder = totalCount % pageCond.getPageSize();
		int totalPage = totalCount / pageCond.getPageSize();
		if (remainder != 0) {
			totalPage++;
		}

		pageRst.setTotalPage(totalPage);
		pageRst.setLastPage(pageCond.getPageNo() == totalPage);

		return pageRst;
	}

	@Override
	public PageRst<FltInfoDto> getFlightsByPlaceCond(PageCond<ArrdepPlaceCond> pageCond) {
		List<FltInfoDto> rst = new ArrayList<>();
		PageRst<FltInfoDto> pageRst = new PageRst<>();
		pageRst.setFirstPage(pageCond.getPageNo() == 1);
		pageRst.setRst(rst);
		int totalCount = 0;
		List<Airport> airports = basService.getAirportsByCityCode(pageCond.getCond().getCode());

		if (airports == null || airports.size() == 0)
			return null;

		ArrdepPlaceCond cond = pageCond.getCond();
		if (ArrdepFlag.A == pageCond.getCond().getArrdep()) {
			List<String> airportCodes = new ArrayList<>();
			for (Airport airport : airports) {
				airportCodes.add(airport.getAirportCode());
			}

			totalCount = fltArrfPekDao.selectTotalCountByPlaceCond(airportCodes, cond.getAirlineCode(),
					cond.getQueryDate());
			List<ArrfPek> arrfList = fltArrfPekDao.selectByPlaceCond(pageCond.getPageNo(), pageCond.getPageSize(),
					airportCodes, cond.getAirlineCode(), cond.getQueryDate());

			if (arrfList == null || arrfList.size() == 0)
				return null;

			for (ArrfPek arrf : arrfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByArrfPek(arrf, fltInfo);

				rst.add(fltInfo);
			}
		} else {
			List<String> airportCodes = new ArrayList<>();
			for (Airport airport : airports) {
				airportCodes.add(airport.getAirportCode());
			}

			totalCount = fltDepfPekDao.selectTotalCountByPlaceCond(airportCodes, cond.getAirlineCode(),
					cond.getQueryDate());
			List<DepfPek> depfList = fltDepfPekDao.selectByPlaceCond(pageCond.getPageNo(), pageCond.getPageSize(),
					airportCodes, cond.getAirlineCode(), cond.getQueryDate());

			if (depfList == null || depfList.size() == 0)
				return null;

			for (DepfPek depf : depfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByDepfPek(depf, fltInfo);

				rst.add(fltInfo);
			}
		}

		pageRst.setTotalCount(totalCount);
		int remainder = totalCount % pageCond.getPageSize();
		int totalPage = totalCount / pageCond.getPageSize();
		if (remainder != 0) {
			totalPage++;
		}

		pageRst.setTotalPage(totalPage);
		pageRst.setLastPage(pageCond.getPageNo() == totalPage);

		return pageRst;
	}

	@Override
	public FltDetailDto getFlightDetailByFltIdCond(FltIdCond cond) {
		FltDetailDto fltInfo = new FltDetailDto();

		if (ArrdepFlag.A == cond.getArrdep()) {
			ArrfPek arrf = fltArrfPekDao.selectByArrfId(cond.getFltId());

			if (arrf == null)
				return null;

			assignByArrfPek(arrf, fltInfo);
			fltInfo.setFltType(arrf.getFltType());

			if (FltTypeConst.MAIN.equals(fltInfo.getFltType())) {
				fltInfo.setRelFltDesc(FltTypeConst.SHARE_DESC);
				// 拼接共享航班号
				fltInfo.setRelFltNos(getArrfShareFltNos(arrf));

			} else if (FltTypeConst.SHARE.equals(fltInfo.getFltType())) {
				fltInfo.setRelFltDesc(FltTypeConst.MAIN_DESC);
				// 获取主航班号
				fltInfo.setRelFltNos(arrf.getMasterFltNo());
			} else {
				// 独立航班不显示任何信息
			}

			// 赋值应该显示的时间
			assignByArrfPek(arrf, fltInfo);

			if (!StringUtils.isNullOrEmpty(arrf.getBltDisp())) {
				fltInfo.setBltDisp(arrf.getBltDisp());
			}
			if (arrf.getFirstBltOt() != null) {
				fltInfo.setFirstBltOt(DateUtils.formatDate(arrf.getFirstBltOt(), "HH:mm"));
			}
		} else {
			DepfPek depf = fltDepfPekDao.selectByDepfId(cond.getFltId());

			if (depf == null)
				return null;

			assignByDepfPek(depf, fltInfo);
			fltInfo.setFltType(depf.getFltType());

			if (FltTypeConst.MAIN.equals(fltInfo.getFltType())) {
				fltInfo.setRelFltDesc(FltTypeConst.SHARE_DESC);
				// 拼接共享航班号
				fltInfo.setRelFltNos(getDepfShareFltNos(depf));
			} else if (FltTypeConst.SHARE.equals(fltInfo.getFltType())) {
				fltInfo.setRelFltDesc(FltTypeConst.MAIN_DESC);
				// 获取主航班号
				fltInfo.setRelFltNos(depf.getMasterFltNo());
			} else {
				// 独立航班不显示任何信息
			}

			// 赋值应该显示的时间
			assignByDepfPek(depf, fltInfo);

			if (!StringUtils.isNullOrEmpty(depf.getCntDisp())) {
				fltInfo.setCntDisp(depf.getCntDisp());
			}
			if (depf.getFirstCntOt() != null) {
				fltInfo.setFirstCntOt(DateUtils.formatDate(depf.getFirstCntOt(), "HH:mm"));
			}

			if (!StringUtils.isNullOrEmpty(depf.getGatDisp())) {
				fltInfo.setGatDisp(depf.getGatDisp());
			}
			if (depf.getFirstGatOt() != null) {
				fltInfo.setFirstGatOt(DateUtils.formatDate(depf.getFirstGatOt(), "HH:mm"));
			}
		}

		// 查询是否有效的关注
		SubscriberCond suberCond = new SubscriberCond();
		suberCond.setSubscriberId(cond.getSubscriberId());
		SubscribeContract contract = subscribeService.getContractByFltIdCond(cond);
		if (contract != null) {
			fltInfo.setFollow(true);
			fltInfo.setContractId(contract.getContractId());
		}

		return fltInfo;
	}

	/**
	 * 根据到港航班动态赋值航班信息
	 * 
	 * @param arrf
	 *            到港航班动态
	 * @param fltInfo
	 *            航班信息
	 */
	private void assignByArrfPek(ArrfPek arrf, FltInfoDto fltInfo) {
		if (fltInfo == null)
			fltInfo = new FltInfoDto();

		fltInfo.setFltId(arrf.getArrfId());
		fltInfo.setFltNo(arrf.getFltNo());
		fltInfo.setArrdep(ArrdepFlag.A);
		fltInfo.setIata(arrf.getFltNo().substring(0, 2));
		fltInfo.setAirlineNameCn(basService.getAirlineNameCnByCode(fltInfo.getIata()));
		fltInfo.setStartSdt(DateUtils.formatDate(arrf.getStartSdt(), "HH:mm"));
		fltInfo.setStartAirportCn(arrf.getStartAirportCn() == null ? "未到达" : arrf.getStartAirportCn());
		fltInfo.setDestSdt(DateUtils.formatDate(arrf.getSdt(), "HH:mm"));
		fltInfo.setDestAirportCn(currentAirportCn);
		fltInfo.setStateCn(arrf.getFltStateCnAbbr());
	}

	/**
	 * 根据离港航班动态赋值航班信息
	 * 
	 * @param arrf
	 *            离港航班动态
	 * @param fltInfo
	 *            航班信息
	 */
	private void assignByDepfPek(DepfPek depf, FltInfoDto fltInfo) {
		if (fltInfo == null)
			fltInfo = new FltInfoDto();

		fltInfo.setFltId(depf.getDepfId());
		fltInfo.setFltNo(depf.getFltNo());
		fltInfo.setArrdep(ArrdepFlag.D);
		fltInfo.setIata(depf.getFltNo().substring(0, 2));
		fltInfo.setAirlineNameCn(basService.getAirlineNameCnByCode(fltInfo.getIata()));
		fltInfo.setStartSdt(DateUtils.formatDate(depf.getSdt(), "HH:mm"));
		fltInfo.setStartAirportCn(currentAirportCn);
		fltInfo.setDestSdt(DateUtils.formatDate(depf.getDestSdt(), "HH:mm"));
		fltInfo.setDestAirportCn(depf.getDestAirportCn());
		fltInfo.setStateCn(depf.getFltStateCnAbbr() == null ? "未开放值机" : depf.getFltStateCnAbbr());
	}

	/**
	 * 获取到港航班的共享航班号串
	 * 
	 * @param arrf
	 *            到港航班信息
	 * @return 共享航班号串
	 */
	private String getArrfShareFltNos(ArrfPek arrf) {
		String sfltNos = "";

		if (!StringUtils.isNullOrEmpty(arrf.getSflight1())) {
			sfltNos += arrf.getSflight1();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight2())) {
			sfltNos += "," + arrf.getSflight2();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight3())) {
			sfltNos += "," + arrf.getSflight3();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight4())) {
			sfltNos += "," + arrf.getSflight4();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight5())) {
			sfltNos += "," + arrf.getSflight5();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight6())) {
			sfltNos += "," + arrf.getSflight6();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight7())) {
			sfltNos += "," + arrf.getSflight7();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight8())) {
			sfltNos += "," + arrf.getSflight8();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight9())) {
			sfltNos += "," + arrf.getSflight9();
		}
		if (!StringUtils.isNullOrEmpty(arrf.getSflight10())) {
			sfltNos += "," + arrf.getSflight10();
		}

		return sfltNos;
	}

	/**
	 * 获取离港航班的共享航班号串
	 * 
	 * @param depf
	 *            离港航班信息
	 * @return 共享航班号串
	 */
	private String getDepfShareFltNos(DepfPek depf) {
		String sfltNos = "";

		if (!StringUtils.isNullOrEmpty(depf.getSflight1())) {
			sfltNos += depf.getSflight1();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight2())) {
			sfltNos += "," + depf.getSflight2();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight3())) {
			sfltNos += "," + depf.getSflight3();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight4())) {
			sfltNos += "," + depf.getSflight4();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight5())) {
			sfltNos += "," + depf.getSflight5();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight6())) {
			sfltNos += "," + depf.getSflight6();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight7())) {
			sfltNos += "," + depf.getSflight7();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight8())) {
			sfltNos += "," + depf.getSflight8();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight9())) {
			sfltNos += "," + depf.getSflight9();
		}
		if (!StringUtils.isNullOrEmpty(depf.getSflight10())) {
			sfltNos += "," + depf.getSflight10();
		}

		return sfltNos;
	}

	/**
	 * 根据到港航班信息赋值航班详情数据传输对象
	 * 
	 * @param arrf
	 *            到港航班
	 * @param fltDetailDto
	 *            航班详情数据传输对象
	 */
	private void assignByArrfPek(ArrfPek arrf, FltDetailDto fltDetailDto) {
		// 预计飞行时长（毫秒）
		long diff = 0;
		if (arrf.getStartSdt() != null) {
			diff = arrf.getSdt().getTime() - arrf.getStartSdt().getTime();
		}
		// 如果实际时间不为空取实际；实际时间为空取预计时间；预计时间为空取计划时间
		Date destTime;
		if (arrf.getActTime() != null) {
			fltDetailDto.setDestTimeName("实际到达");
			destTime = arrf.getActTime();
		} else if (arrf.getEstTime() != null) {
			fltDetailDto.setDestTimeName("预计到达");
			destTime = arrf.getEstTime();
		} else {
			fltDetailDto.setDestTimeName("预计到达");
			destTime = arrf.getSdt();
		}

		Date startTime = new Date(destTime.getTime() - diff);
		fltDetailDto.setStartTime(DateUtils.formatDate(startTime, "HH:mm"));
		fltDetailDto.setDestTime(DateUtils.formatDate(destTime, "HH:mm"));
		fltDetailDto.setStartTimeName("预计起飞");
	}

	/**
	 * 根据离港航班信息赋值航班详情数据传输对象
	 * 
	 * @param arrf
	 *            离港航班
	 * @param fltDetailDto
	 *            航班详情数据传输对象
	 */
	private void assignByDepfPek(DepfPek depf, FltDetailDto fltDetailDto) {
		// 预计飞行时长（毫秒）
		long diff = 0;
		if (depf.getDestSdt() != null) {
			diff = depf.getDestSdt().getTime() - depf.getSdt().getTime();
		}
		// 如果实际时间不为空取实际；实际时间为空取预计时间；预计时间为空取计划时间
		Date startTime;
		if (depf.getActTime() != null) {
			fltDetailDto.setStartTimeName("实际起飞");
			startTime = depf.getActTime();
		} else if (depf.getEstTime() != null) {
			fltDetailDto.setStartTimeName("预计起飞");
			startTime = depf.getEstTime();
		} else {
			fltDetailDto.setStartTimeName("预计起飞");
			startTime = depf.getSdt();
		}

		Date destTime = new Date(startTime.getTime() + diff);
		fltDetailDto.setStartTime(DateUtils.formatDate(startTime, "HH:mm"));
		fltDetailDto.setDestTime(DateUtils.formatDate(destTime, "HH:mm"));
		fltDetailDto.setDestTimeName("预计到达");
	}

	@Override
	public PageRst<FltInfoDto> getFollowedFlights(SubscriberCond cond) {
		List<SubscribeContract> contracts = subscribeService.getEffectContractsBySubscriberCond(cond);

		if (contracts == null || contracts.size() == 0)
			return null;

		List<String> depfIds = new ArrayList<>();
		List<String> arrfIds = new ArrayList<>();
		Map<String, SubscribeContract> map = new HashMap<>();

		for (SubscribeContract contract : contracts) {
			String keywords = contract.getSubscribeKeywords();
			String[] strArray = keywords.split("\\|");
			if (strArray[1].equals(ArrdepFlag.A.toString())) {
				arrfIds.add(strArray[0].trim());
			} else {
				depfIds.add(strArray[0].trim());
			}

			map.put(strArray[0].trim(), contract);
		}

		PageRst<FltInfoDto> pageRst = new PageRst<>();
		List<FltInfoDto> rst = new ArrayList<>();
		pageRst.setRst(rst);

		if (arrfIds != null && arrfIds.size() > 0) {
			List<ArrfPek> arrfList = fltArrfPekDao.selectByArrfIds(arrfIds);
			for (ArrfPek arrf : arrfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByArrfPek(arrf, fltInfo);
				fltInfo.setContractId(map.get(arrf.getArrfId()).getContractId());
				rst.add(fltInfo);
			}
		}
		if (depfIds != null && depfIds.size() > 0) {
			List<DepfPek> depfList = fltDepfPekDao.selectByDepfIds(depfIds);
			for (DepfPek depf : depfList) {
				FltInfoDto fltInfo = new FltInfoDto();
				assignByDepfPek(depf, fltInfo);
				fltInfo.setContractId(map.get(depf.getDepfId()).getContractId());
				rst.add(fltInfo);
			}
		}

		return pageRst;
	}

	/**
	 * 计算到港航班状态
	 * 
	 * @param arrfPek
	 *            到港航班
	 */
	private void caculateArrfState(ArrfPek arrfPek) {
		String stateCnAbbr;

		switch (arrfPek.getDelayReasonCode()) {
		case "DVT":
			stateCnAbbr = "备降";
			break;
		case "RTN":
			stateCnAbbr = "返航";
			break;
		case "CAN":
			stateCnAbbr = "取消";
			break;
		case "DLY":
			if (arrfPek.getActTime() != null) {
				stateCnAbbr = "已到达";
			} else {
				stateCnAbbr = "延误";
			}
			break;
		default:
			stateCnAbbr = "未到达";
			break;
		}

		arrfPek.setFltStateCnAbbr(stateCnAbbr);
	}

	/**
	 * 计算离港航班状态
	 * 
	 * @param depfPek
	 *            离港航班
	 */
	private void caculateDepfState(DepfPek depfPek) {
		String stateCnAbbr;

		switch (depfPek.getDelayReasonCode()) {
		case "DVT":
			stateCnAbbr = "备降";
			break;
		case "RTN":
			stateCnAbbr = "返航";
			break;
		case "CAN":
			stateCnAbbr = "取消";
			break;
		case "DLY":
			if (depfPek.getActTime() != null) {
				stateCnAbbr = "已起飞";
			} else if (depfPek.getLastTime() != null) {
				stateCnAbbr = "最后登机提示";
			} else if (depfPek.getFltStateCode() == "BOB") {
				stateCnAbbr = "开始登机";
			} else {
				stateCnAbbr = "延误";
			}
			break;
		default:
			if (depfPek.getFirstCntOt() != null) {
				stateCnAbbr = "开始值机";
			} else {
				stateCnAbbr = "未开放值机";
			}
			break;
		}

		depfPek.setFltStateCnAbbr(stateCnAbbr);
	}

	@Override
	public List<FltInfoDto> getLimitFlightsByShakeCond(ShakeCond cond) {
		return getFlightsByShakeCond(cond, 2);
	}

	@Override
	public List<FltInfoDto> getFlightsByShakeCond(ShakeCond cond) {
		return getFlightsByShakeCond(cond, -1);
	}

	private List<FltInfoDto> getFlightsByShakeCond(ShakeCond cond, int limit) {
		List<String> gateNos = new ArrayList<>();
		if (cond.getBeaconId().equals("1")) {
			gateNos.add("C30");
		} else {
			gateNos.add("C51");
			gateNos.add("C52");
		}

		List<DepfPek> depfs = fltDepfPekDao.selectByGateNos(gateNos, limit);
		if (depfs == null || depfs.size() == 0)
			return null;

		List<FltInfoDto> rst = new ArrayList<>();
		for (DepfPek depf : depfs) {
			FltInfoDto fltInfo = new FltInfoDto();
			assignByDepfPek(depf, fltInfo);

			rst.add(fltInfo);
		}

		return rst;
	}

	/**
	 * 获取属性值不同的集合
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPropertyDifferences(Object origin, Object current, List<String> ignoredFields) {
		if (origin == null || current == null)
			return null;

		Class<Object> oClass = (Class<Object>) origin.getClass();
		Class<Object> cClass = (Class<Object>) current.getClass();

		Field[] fields = cClass.getDeclaredFields();

		if (fields == null)
			return null;

		Map<String, Object> rst = new HashMap<>();

		for (Field field : fields) {
			if (ignoredFields != null && ignoredFields.contains(field.getName())) {
				continue;
			}

			// 如果字段是boolean类型，自动生成的get方法不加get前缀？？
			String methodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);

			try {
				Method om = (Method) oClass.getMethod(methodName);
				Method cm = (Method) cClass.getMethod(methodName);

				Object oVal = om.invoke(origin);
				Object cVal = cm.invoke(current);

				if (cVal == null) {
					if (oVal != null) {
						rst.put(field.getName(), null);
					}
				} else if (!cVal.equals(oVal)) {
					if (cVal.getClass() == String.class) {
						rst.put(field.getName(), String.valueOf(cVal));
					} else if (cVal.getClass() == Date.class) {
						Date d = (Date) cVal;
						rst.put(field.getName(),
								DateUtils.formatDate(d, DateUtils.formatDate(d, DatePatterns.POPULAR_DATE24TIME)));
					} else if (cVal.getClass() == Integer.class) {
						Integer d = (Integer) cVal;
						rst.put(field.getName(), Integer.valueOf(d));
					} else {
						rst.put(field.getName(), String.valueOf(cVal));
					}
				}
			} catch (NoSuchMethodException e) {
			} catch (SecurityException e) {
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return rst;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void execute(Element data) {
		JaxbUtils arrfJaxb = new JaxbUtils(ArrfPek.class);
		JaxbUtils depfJaxb = new JaxbUtils(DepfPek.class);
		Iterator iterator = data.elementIterator();
		while (iterator.hasNext()) {
			Element e = (Element) iterator.next();
			// logger.info(e.asXML());

			try {
				if (e.getName().toUpperCase().equals("INBO")) {
					ArrfPek arrf = arrfJaxb.<ArrfPek> fromXml(e.asXML());
					System.out.println("ArrfPek解析成功!");
					handleArrfPek(arrf);
				} else {
					DepfPek depf = depfJaxb.<DepfPek> fromXml(e.asXML());
					System.out.println("DepfPek解析成功!");
					handleDepfPek(depf);
				}
			} catch (Exception e2) {
				logger.error(e2.toString());
				e2.printStackTrace();
			}
		}
	}

	private void handleArrfPek(ArrfPek arrf) {
		if (arrf == null)
			return;

		// 计算扩展字段
		caculateExtendFields(arrf);

		// 获取原到港航班动态
		ArrfPek originArrfPek = getByArrfId(arrf.getArrfId());
		// 原航班动态不存在，直接保存，返回
		if (originArrfPek == null) {
			arrf.setCreateTime(new Date());
			saveArrfPek(arrf);
			beltPekDao.batchInsert(arrf.getBelts());
			return;
		}

		// 获取航班动态的差异
		Map<String, Object> differences = getPropertyDifferences(originArrfPek, arrf, arrfIgnoredFields);

		if (differences == null || differences.size() == 0)
			return;

		// 更新航班动态
		updateArrfPekDifferences(arrf, differences);
		if (differences.containsKey("bltDisp") || differences.containsKey("firstBltOt")) {
			// TODO:判断是否出现变更
			beltPekDao.batchInsertAfterDelete(arrf.getBelts());
		}

		// 组织航班动态变更信息，保存航班变更信息
		String changeInfo = getChangeInfo(arrf);
		if (StringUtils.isNullOrEmpty(changeInfo))
			return;

		FltChangeinfoPek changeinfo = new FltChangeinfoPek();
		changeinfo.setAlcd(arrf.getFltNo().substring(0, 2));
		changeinfo.setArrdep(ArrdepFlag.A.toString());
		changeinfo.setChangeInfo(changeInfo);
		changeinfo.setCreateTime(new Date());
		changeinfo.setDomint(arrf.getDomint());
		changeinfo.setExecuteFlag("N");
		changeinfo.setFltId(arrf.getArrfId());
		changeinfo.setFltNo(arrf.getFltNo());
		changeinfo.setSdt(arrf.getSdt());

		fltChangeinfoPekDao.insert(changeinfo);
	}

	/**
	 * 计算扩展字段
	 */
	private void caculateExtendFields(ArrfPek arrf) {
		if (arrf == null)
			return;

		// 状态
		caculateArrfState(arrf);
		// 转盘、第一开放时间
		if (arrf.getBelts() == null || arrf.getBelts().size() == 0) {
			arrf.setBltDisp(null);
			arrf.setFirstBltOt(null);
		} else {
			String bltDisp = "";

			for (ArrfBeltPek belt : arrf.getBelts()) {
				bltDisp += "," + belt.getBltCode();

				if (arrf.getFirstBltOt() == null) {
					arrf.setFirstBltOt(belt.getBltFbagTime());
				} else {
					if (belt.getBltFbagTime() != null
							&& belt.getBltFbagTime().getTime() < arrf.getFirstBltOt().getTime()) {
						arrf.setFirstBltOt(belt.getBltFbagTime());
					}
				}
			}

			arrf.setBltDisp(bltDisp.substring(1, bltDisp.length()));
		}

		// belts
		if (arrf.getBelts() != null && arrf.getBelts().size() > 0) {
			for (ArrfBeltPek belt : arrf.getBelts()) {
				belt.setArrfId(arrf.getArrfId());
				belt.setFltNo(arrf.getFltNo());
				belt.setSdt(arrf.getSdt());
			}
		}
	}

	/**
	 * 对比到港航班动态的需通知信息
	 * 
	 * @param newArrfPek
	 *            新的到港航班动态
	 * @return 需要通知的变更信息
	 */
	private String getChangeInfo(ArrfPek newArrfPek) {
		ArrfPek originArrfPek = fltArrfPekDao.selectByArrfId(newArrfPek.getArrfId());

		if (originArrfPek == null)
			return null;

		StringBuilder changeInfo = new StringBuilder(64);

		/*
		 * 需要通知的动态变更
		 */
		if (!DateUtils.compare(newArrfPek.getEstTime(), originArrfPek.getEstTime())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.estTime,
					DateUtils.formatDate(newArrfPek.getEstTime(), DatePatterns.POPULAR_DATE24TIME)));
		}
		if (!DateUtils.compare(newArrfPek.getActTime(), originArrfPek.getActTime())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.actTime,
					DateUtils.formatDate(newArrfPek.getActTime(), DatePatterns.POPULAR_DATE24TIME)));
		}
		if (!StringUtils.compareIngoreEmpty(newArrfPek.getBltDisp(), (originArrfPek.getBltDisp()))) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.bltDisp, newArrfPek.getBltDisp()));
		}
		if (!StringUtils.compareIngoreEmpty(newArrfPek.getTerm(), originArrfPek.getTerm())
				|| !StringUtils.compareIngoreEmpty(newArrfPek.getExitsNo(), originArrfPek.getExitsNo())) {
			changeInfo.append(String.format("%s:%s-%s|", FltChangeinfoFiledsConst.termOrExitsNo, newArrfPek.getTerm(),
					newArrfPek.getExitsNo()));
		}
		if (!StringUtils.compareIngoreEmpty(newArrfPek.getFltStateCnAbbr(), originArrfPek.getFltStateCnAbbr())) {
			changeInfo.append(
					String.format("%s:%s|", FltChangeinfoFiledsConst.fltStateCnAbbr, newArrfPek.getFltStateCnAbbr()));
		}

		return changeInfo.toString();
	}

	private void handleDepfPek(DepfPek depf) {
		if (depf == null)
			return;

		// 计算扩展字段
		caculateExtendFields(depf);

		// 获取原到港航班动态
		DepfPek originDepfPek = getByDepfId(depf.getDepfId());
		// 原航班动态不存在，直接保存，返回
		if (originDepfPek == null) {
			depf.setCreateTime(new Date());
			saveDepPek(depf);
			// TODO:判断是否出现变更
			counterPekDao.batchInsert(depf.getCounters());
			gatePekDao.batchInsert(depf.getGates());
			return;
		}

		// 获取航班动态的差异
		Map<String, Object> differences = getPropertyDifferences(originDepfPek, depf, depfIgnoredFields);

		if (differences == null || differences.size() == 0)
			return;

		// 更新航班动态
		updateDepfPekDifferences(depf, differences);
		// 值机柜台资源发生变化
		if (differences.containsKey("cntDisp") || differences.containsKey("firstCntOt")) {
			counterPekDao.batchInsertAfterDelete(depf.getCounters());
		}
		// 登机口资源发生变化
		if (differences.containsKey("gatDisp") || differences.containsKey("firstGatOt")) {
			gatePekDao.batchInsertAfterDelete(depf.getGates());
		}

		// 组织航班动态变更信息，保存航班变更信息
		String changeInfo = getChangeInfo(depf);
		if (StringUtils.isNullOrEmpty(changeInfo))
			return;

		FltChangeinfoPek changeinfo = new FltChangeinfoPek();
		changeinfo.setAlcd(depf.getFltNo().substring(0, 2));
		changeinfo.setArrdep(ArrdepFlag.D.toString());
		changeinfo.setChangeInfo(changeInfo);
		changeinfo.setCreateTime(new Date());
		changeinfo.setDomint(depf.getDomint());
		changeinfo.setExecuteFlag("N");
		changeinfo.setFltId(depf.getDepfId());
		changeinfo.setFltNo(depf.getFltNo());
		changeinfo.setSdt(depf.getSdt());

		fltChangeinfoPekDao.insert(changeinfo);
	}

	/**
	 * 计算扩展字段
	 */
	private void caculateExtendFields(DepfPek depf) {
		if (depf == null)
			return;

		// 状态
		caculateDepfState(depf);
		// 值机柜台、第一开放时间
		if (depf.getCounters() == null || depf.getCounters().size() == 0) {
			depf.setCntDisp(null);
			depf.setFirstCntOt(null);
		} else {
			String cntDisp = "";

			for (DepfCounterPek counter : depf.getCounters()) {
				cntDisp += "," + counter.getCntCode();

				if (depf.getFirstCntOt() == null) {
					depf.setFirstCntOt(counter.getCntOt());
				} else {
					if (counter.getCntOt() != null && counter.getCntOt().getTime() < depf.getFirstCntOt().getTime()) {
						depf.setFirstCntOt(counter.getCntOt());
					}
				}
			}

			depf.setCntDisp(cntDisp.substring(1, cntDisp.length()));
		}

		// 登机口、第一开放时间
		if (depf.getGates() == null || depf.getGates().size() == 0) {
			depf.setGatDisp(null);
			depf.setFirstGatOt(null);
		} else {
			String gatDisp = "";

			for (DepfGatePek gate : depf.getGates()) {
				gatDisp += "," + gate.getGatCode();

				if (depf.getFirstGatOt() == null) {
					depf.setFirstGatOt(gate.getGatOt());
				} else {
					if (gate.getGatOt() != null && gate.getGatOt().getTime() < depf.getFirstGatOt().getTime()) {
						depf.setFirstGatOt(gate.getGatOt());
					}
				}
			}

			depf.setGatDisp(gatDisp.substring(1, gatDisp.length()));
		}

		// counters
		if (depf.getCounters() != null && depf.getCounters().size() > 0) {
			for (DepfCounterPek counter : depf.getCounters()) {
				counter.setDepfId(depf.getDepfId());
				counter.setFltNo(depf.getFltNo());
				counter.setSdt(depf.getSdt());
			}
		}
		// gates
		if (depf.getGates() != null && depf.getGates().size() > 0) {
			for (DepfGatePek gate : depf.getGates()) {
				gate.setDepfId(depf.getDepfId());
				gate.setFltNo(depf.getFltNo());
				gate.setSdt(depf.getSdt());
			}
		}
	}

	/**
	 * 对比离港航班动态的需通知信息
	 * 
	 * @param newDepfPek
	 *            新的离港航班动态
	 * @return 需要通知的变更信息
	 */
	public String getChangeInfo(DepfPek newDepfPek) {
		DepfPek originDepfPek = fltDepfPekDao.selectByDepfId(newDepfPek.getDepfId());

		if (originDepfPek == null)
			return null;

		StringBuilder changeInfo = new StringBuilder(64);

		/*
		 * 需要通知的动态变更
		 */
		if (!DateUtils.compare(newDepfPek.getEstTime(), originDepfPek.getEstTime())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.estTime,
					DateUtils.formatDate(newDepfPek.getEstTime(), DatePatterns.POPULAR_DATE24TIME)));
		}
		if (!DateUtils.compare(newDepfPek.getActTime(), originDepfPek.getActTime())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.actTime,
					DateUtils.formatDate(newDepfPek.getActTime(), DatePatterns.POPULAR_DATE24TIME)));
		}
		if (!DateUtils.compare(newDepfPek.getFirstCntOt(), originDepfPek.getFirstCntOt())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.firstCntOt,
					DateUtils.formatDate(newDepfPek.getFirstCntOt(), DatePatterns.POPULAR_DATE24TIME)));
		}
		if (!StringUtils.compareIngoreEmpty(newDepfPek.getGatDisp(), originDepfPek.getGatDisp())) {
			changeInfo.append(String.format("%s:%s|", FltChangeinfoFiledsConst.gatDisp, newDepfPek.getGatDisp()));
		}
		if (!StringUtils.compareIngoreEmpty(newDepfPek.getFltStateCnAbbr(), originDepfPek.getFltStateCnAbbr())) {
			changeInfo.append(
					String.format("%s:%s|", FltChangeinfoFiledsConst.fltStateCnAbbr, newDepfPek.getFltStateCnAbbr()));
		}

		return changeInfo.toString();
	}

	private ArrfPek getByArrfId(String arrfId) {
		// 读取缓存
		ArrfPek arrf = FltCache.<ArrfPek> get(arrfId);

		if (arrf == null) {
			arrf = fltArrfPekDao.selectByArrfId(arrfId);

			if (arrf != null) {
				FltCache.put(arrfId, arrf);
			}
		}

		return arrf;
	}

	private void saveArrfPek(ArrfPek arrf) {
		String arrfId = fltArrfPekDao.insert(arrf);

		if (StringUtils.isNullOrEmpty(arrfId))
			return;

		// 新增缓存
		FltCache.put(arrfId, arrf);
	}

	public void updateArrfPekDifferences(ArrfPek newArrfPek, Map<String, Object> differences) {
		int i = fltArrfPekDao.update(newArrfPek.getArrfId(), differences);

		if (i <= 0)
			return;

		// 更新缓存
		FltCache.put(newArrfPek.getArrfId(), newArrfPek);
	}

	private DepfPek getByDepfId(String depfId) {
		DepfPek depf = FltCache.<DepfPek> get(depfId);

		if (depf == null) {
			depf = fltDepfPekDao.selectByDepfId(depfId);

			if (depf != null) {
				FltCache.put(depfId, depf);
			}
		}

		return depf;
	}

	private void saveDepPek(DepfPek depf) {
		String depfId = fltDepfPekDao.insert(depf);

		if (StringUtils.isNullOrEmpty(depfId))
			return;

		// 新增缓存
		FltCache.put(depfId, depf);
	}

	public void updateDepfPekDifferences(DepfPek newDepfPek, Map<String, Object> differences) {
		int i = fltDepfPekDao.update(newDepfPek.getDepfId(), differences);

		if (i <= 0)
			return;

		// 更新缓存
		FltCache.put(newDepfPek.getDepfId(), newDepfPek);
	}
}
