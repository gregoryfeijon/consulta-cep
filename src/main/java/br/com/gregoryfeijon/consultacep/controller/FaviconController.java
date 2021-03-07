package br.com.gregoryfeijon.consultacep.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 06/03/2021 às 23:20:50
 * 
 * @author gregory.feijon
 */

@Controller
public class FaviconController {

	@GetMapping("favicon.ico")
	@ResponseBody
	void returnNoFavicon() {
	}
}
