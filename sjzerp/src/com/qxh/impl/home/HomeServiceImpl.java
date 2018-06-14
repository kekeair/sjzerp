package com.qxh.impl.home;

import org.apache.log4j.Logger;

import com.qxh.service.HomeService;

public class HomeServiceImpl implements HomeService {

	Logger log = Logger.getLogger(this.getClass());
	private HomeMapper homeMapper;

	public void setHomeMapper(HomeMapper homeMapper) {
		this.homeMapper = homeMapper;
	}


}
