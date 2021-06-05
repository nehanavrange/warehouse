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

import in.nit.model.UOMType;
import in.nit.service.IUOMService;

@Controller
@RequestMapping("/uom")
public class UOMTypeController {

	@Autowired
	private IUOMService service;
	
	@GetMapping("/register")
	public  String showRegister(Model model)
	{
		//form backing obj
		model.addAttribute("uomType", new UOMType());
		return "UOMRegister";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute UOMType uomtype,Model model) {
		Integer id=service.saveUOMType(uomtype);
		String msg="UOMType ' "+id+" ' saved";
	    model.addAttribute("message", msg);
	  //form backing obj
	    model.addAttribute("uomType", new UOMType());
	    return "UOMRegister";	
	}
	@GetMapping("/all")
	public String showAll(Model model)
	{
		List<UOMType> list=service.getAllUOMType();
		model.addAttribute("list", list);
		return "UOMData";
	}
	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id,Model model)
	{
		String msg=null;
		if(service.isUOMTypeExist(id))
         {
        	 service.deleteUOMType(id);
        	 msg="UOMType ' "+id+ " ' deleted";
          }
         else {
        	 msg="UOMType ' "+id+ " ' not exist";
         }
         model.addAttribute("message", msg);
         List<UOMType> list=service.getAllUOMType();
 		model.addAttribute("list", list);
		return "UOMData";
	}
	
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model)
	{
		String page=null;
		Optional<UOMType>opt=service.getOneUOMType(id);
		if(opt.isPresent())
		{
			UOMType st=opt.get();
			model.addAttribute("uom",st);
			page="UOMEdit";
		}
		else {
			return "redirect:../all";
		}
		return page;
	}
	@PostMapping("/update")
	public String update(@ModelAttribute UOMType uom,Model model)
	{
		service.updateUOMType(uom);
		String msg="UOMType '"+uom.getId()+ "' Updated";
		model.addAttribute("message", msg);
		
		//fetch new data
		List<UOMType>list=service.getAllUOMType();
		model.addAttribute("list",list);
		return "UOMData";
		
	}

    // view the page
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id,Model model) {
    	String page=null;
		Optional<UOMType>opt=service.getOneUOMType(id);
		if(opt.isPresent()) {
			UOMType om=opt.get();
			model.addAttribute("ob",om);
			page="UOMTypeView";
		}
		else {
			return "redirect:../all";
		}
		return page;
    	
    	
    }
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
