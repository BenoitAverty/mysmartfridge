package com.mysmartfridge;

import java.net.URI;
import java.util.UUID;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
	public static URI buildUriForUuid(UUID uuid) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(uuid).toUri();
	}
}
