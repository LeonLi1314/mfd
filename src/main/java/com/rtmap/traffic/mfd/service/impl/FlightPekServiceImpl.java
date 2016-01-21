package com.rtmap.traffic.mfd.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rtmap.traffic.mfd.dao.IFltArrfPekDao;
import com.rtmap.traffic.mfd.dao.IFltDepfPekDao;
import com.rtmap.traffic.mfd.domain.ArrdepFlag;
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
import com.rtmap.traffic.mfd.domain.entity.ArrfPek;
import com.rtmap.traffic.mfd.domain.entity.DepfPek;
import com.rtmap.traffic.mfd.domain.entity.SubscribeContract;
import com.rtmap.traffic.mfd.service.IBasService;
import com.rtmap.traffic.mfd.service.IFlightService;
import com.rtmap.traffic.mfd.service.ISubscribeService;

import lqs.frame.util.DateUtils;
import lqs.frame.util.StringUtils;

/**
 * 航班动态服务层实现
 * 
 * @author liqingshan 2016-01-11
 *
 */
@Service("pekService")
public class FlightPekServiceImpl implements IFlightService {
	@Resource
	private IFltArrfPekDao fltArrfPekDao;
	@Resource
	private IFltDepfPekDao fltDepfPekDao;
	@Resource
	private IBasService basService;
	@Resource
	private ISubscribeService subscribeService;
	private String currentAirportCn = "北京";

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
		fltInfo.setStartSdt(arrf.getStartSdt());
		fltInfo.setStartAirportCn(arrf.getStartAirportCn());
		fltInfo.setDestSdt(arrf.getSdt());
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
		fltInfo.setStartSdt(depf.getSdt());
		fltInfo.setStartAirportCn(currentAirportCn);
		fltInfo.setDestSdt(depf.getSdt());
		fltInfo.setDestAirportCn(depf.getDestAirportCn());
		fltInfo.setStateCn(depf.getFltStateCnAbbr());
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
	}

	@Override
	public List<FltInfoDto> getLimitFlightsByShakeCond(ShakeCond cond) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FltInfoDto> getFlightsByShakeCond(ShakeCond cond) {
		// TODO Auto-generated method stub
		return null;
	}
}
