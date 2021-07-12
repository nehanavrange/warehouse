package in.nit.controller;

import java.util.List;
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

import in.nit.model.WhUserType;
import in.nit.service.IWhUserTypeService;
import in.nit.util.EmailUtil;

@Controller
@RequestMapping("/whusertype")
public class WhUserTypeController {
	
	@Autowired
	private IWhUserTypeService service;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@GetMapping("/register")
	public String showRegister(Model model)
	{
		//form backing object
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}
    @PostMapping("/save")
	public String save(@ModelAttribute WhUserType whUserType,Model model)
	{
		Integer id=service.saveWhUserType(whUserType);
		
		String msg="WhUserType data ' "+id+" ' save";
		
		//send email on save
		if(id!=0) {
		boolean flag=emailUtil.send(whUserType.getUserMail(), "Welcome", "Hello User:"+whUserType.getUserCode()+",You are type:"+whUserType.getUserIdType());
		
		if(flag) {
			msg=msg+", Email also sent!";
		}
		else {
			msg=msg+", Email not sent!";
		}
		}
		
		
		//msg send to UI
		model.addAttribute("message", msg);
		//to hide data form backing object without data
		model.addAttribute("whUserType", new WhUserType());
		return "WhUserTypeRegister";
	}
    @GetMapping("/all")
    public String showAll(Model model)
    {
    	List<WhUserType> list=service.getAllWhUserType();
    	model.addAttribute("list", list);
		return "WhUserTypeData";
    }
    
    @GetMapping("/delete/{id}")
    public String removeById(@PathVariable Integer id,Model model)
    {
    	String msg=null;
    	if(service.isWhUserTypeExist(id))
    	{
    		service.deleteWhUserType(id);
    		msg="WhUserType ' "+id+ " 'deleted";
    	}
    	else {
    		msg="WhUserType ' "+id+ " 'not Exist";
    	}
    	model.addAttribute("message", msg);
    	//fetch other data
    	List<WhUserType> list=service.getAllWhUserType();
    	model.addAttribute("list", list);
    	return "WhUserTypeData";
    }
	//onclick edit hyperlink on ui
    //read one path variable and fetch data from service if exist send to edit page else redirect to data page
    //show edit page with data
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id,Model model)
    {
    	Optional<WhUserType> opt=service.getOneWhUserType(id);
    	String page=null;
		if(opt.isPresent())
    	{
    		WhUserType st=opt.get();
    		model.addAttribute("whUserType", st);
    		page="WhUserTypeEdit";
    	}
    	else
    		page="redirect:../all";
		return page;
    }
    //onclik update button,read form data and perform update operation send back to data page success msg
    @PostMapping("/update")
    public String update(@ModelAttribute WhUserType whUserType,Model model)
    {
    	service.updateWhUserType(whUserType);
    	String msg="WhUserType ' "+whUserType.getId()+" ' Updated";
    	model.addAttribute("message", msg);
    	//show new data other rows
    	List<WhUserType> list=service.getAllWhUserType();
    	//sending form backing object with data
    	model.addAttribute("list", list);
		return "WhUserTypeData";
    	
    }
    
    // view the page
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id,Model model) {
    	String page=null;
		Optional<WhUserType>opt=service.getOneWhUserType(id);
		if(opt.isPresent()) {
			WhUserType om=opt.get();
			model.addAttribute("ob",om);
			page="WhUserTypeView";
		}
		else {
			return "redirect:../all";
		}
		return page;
    		
    }
    
    //Ajax validation
    @GetMapping("/mailcheck")
    public @ResponseBody String validateEmail(@RequestParam String mail) {
		String msg="";
		if(service.isWhUserTypeEmailExist(mail)) {
			msg=mail+"<b> already exists!</b>";
		}
    	
    	
    	return msg;
    	
    }
    
    
    
    
    

}
