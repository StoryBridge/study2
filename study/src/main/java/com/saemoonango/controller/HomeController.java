package com.saemoonango.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.saemoonango.domain.LocationVO;
import com.saemoonango.domain.MemberDetailVO;
import com.saemoonango.domain.MemberVO;
import com.saemoonango.persistence.LocationDAO;
import com.saemoonango.persistence.QuestionDAO;
import com.saemoonango.service.LocationService;
import com.saemoonango.service.MemberDetailService;
import com.saemoonango.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private QuestionDAO qDao;
	@Autowired // 스프링프레임워크에서만 작동 프레임워크의 종속성을 피하기 위해선 inject를 사용하자!
	private LocationService lService;

	@Inject
	private MemberDetailService mDservice;

	@Inject
	private MemberService mService;

	@RequestMapping(value = "/example", method = RequestMethod.GET)
	public String example(Locale locale, Model model) {
		return "example";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		// System.out.println("HOME CONNECTED..............");
		logger.debug("debug");
		model.addAttribute("serverTime", formattedDate);
		return "home";
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/question/{lno}", method = RequestMethod.GET)
	public HashMap<String, Object> question(@PathVariable int lno) throws Exception {
		HashMap<String, Object> questionData = new HashMap<>();
		questionData.put("question", qDao.read(lno));
		return questionData;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public HashMap<String, Object> location() throws Exception {
		HashMap<String, Object> locationData = new HashMap<>();
		locationData.put("location", lService.read());
		return locationData;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/json", method = RequestMethod.GET)
	public HashMap<String, Object> json() {
		HashMap<String, Object> map = new HashMap<>();
		// System.out.println("JSON CONNETED............");
		map.put("abcaa", "ddeeed");
		return map;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/memberlist/{id}", method = RequestMethod.GET)
	public HashMap<String, Object> memberList(@PathVariable String id) throws Exception {
		HashMap<String, Object> map = new HashMap<>();
		// System.out.println("memberlist CONNETED..... id is " + id);
		map.put("memberlist", mService.read(id));
		return map;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public HashMap<String, Object> getAnswer(MemberDetailVO vo, RedirectAttributes rttr) throws Exception {
		 System.out.println(vo.toString());
		// ModelAndView model = new ModelAndView();
		HashMap<String, Object> map = new HashMap<>();
		// model.setViewName("home");
		boolean certi = mDservice.certificate(vo);
		if (certi == true) {
			// System.out.println("푼문제임");
			map.put("already", "이미 푼 문제입니다.");
		} else {
			// System.out.println("안푼문제");
			map.put("already", "정답입니다.");
			mDservice.insert(vo);
			mService.totalPoint(); // 맞춘 문제 합해서 member table 업데이트
		}
		// System.out.println(rttr);
		// rttr.addFlashAttribute("result", "success");
		return map;
	}

	@CrossOrigin
	@ResponseBody
	@RequestMapping(value = "/myLocation", method = RequestMethod.POST)
	public void myLocation(MemberVO vo) throws Exception {
		// System.out.println("myLocation Connectedd..........");
		// System.out.println(vo.toString());
		mService.myLocation(vo);
	}

}
