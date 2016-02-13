package com.mysmartfridge;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
	public static URI buildUriForTid(Long tid) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{tid}").buildAndExpand(tid).toUri();
	}
}
