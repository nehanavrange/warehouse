package in.nit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nit.model.OrderMethod;
import in.nit.service.IOrderMethodService;

@Controller
@RequestMapping("/ordermethod")
public class OrderMethodController {

	@Autowired
	IOrderMethodService service;

	// 1. show register page
	@GetMapping("/register")
	public String showRegister(Model model) {
		//form backing obj
		model.addAttribute("orderMethod",new OrderMethod());
		return "OrderMethodRegister";
	}

	//2.save data
	@PostMapping("/save")
	public String save(@ModelAttribute OrderMethod orderMethod,Model model) {

		Integer id=service.saveOrderMethod(orderMethod);
		String message="OrderMethod ' "+id+" ' saved";
		model.addAttribute("message", message);

		//to clear form data and show again register page
		model.addAttribute("orderMethod",new OrderMethod());
		return "OrderMethodRegister";
	}

	//3. show all 
	@GetMapping("/all")
	public String showAll(Model model) {
		model.addAttribute("list",service.getAllOrderMethod());
		return "OrderMethodData";
	}

	//4. show edit page
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<OrderMethod> om=service.getOneOrderMethod(id);
		if(om.isPresent()) {
			//if id is present then getting that object from controller to UI
			model.addAttribute("orderMethod", om.get());
			page="OrderMethodEdit";
		}
		else {
			return "redirect:../all";
		}

		return page;
	}

	// do upadate
	@PostMapping("/update")
	public String update(@ModelAttribute OrderMethod orderMethod,Model model ) {
		service.updateOrderMethod(orderMethod);
		//sending message 
		model.addAttribute("message","OrderMethod '"+orderMethod.getId()+"' Updated!" );

		//sending list also
		model.addAttribute("list",service.getAllOrderMethod());

		return "OrderMethodData";
	}
	
	//5 delete
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id,Model model){
		String msg=null;
		if(service.isOrderMethodExist(id)) {
			service.deleteOrderMethod(id);
			msg="OrderMethod '"+id+"' Deleted!";
		}
		else {
			msg="OrderMethod '"+id+"' not existed!";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("list",service.getAllOrderMethod());
		
		return "OrderMethodData";
		
	}

	//6 show view page
	@GetMapping("/view/{id}")
	public String showView(@PathVariable Integer id,Model model) {
		String page=null;
		Optional<OrderMethod>opt=service.getOneOrderMethod(id);
		if(opt.isPresent()) {
			OrderMethod om=opt.get();
			model.addAttribute("ob",om);
			page="OrderMethodView";
		}
		else {
			return "redirect:../all";
		}
		return page;
	}
	
	//Ajax validation
	@GetMapping("/validatecode")
	public @ResponseBody String validateCode(@RequestParam  String code) {
		String msg="";
		if(service.isOrderMethodCodeExist(code)) {
			msg="<b>Order Method '" +code+"' already exist!</b> ";
		}
		
		return msg;
		
	}
			
	

}
