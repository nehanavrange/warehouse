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

import in.nit.model.ShipmentType;
import in.nit.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipmenttpye")
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;
	
	@GetMapping("/register")
	public String showRegister(Model model)
	{
		//form backing object
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
    @PostMapping("/save")
	public String save(@ModelAttribute ShipmentType shipmentType,Model model)
	{
		Integer id=service.saveShipmentType(shipmentType);
		String msg="ShipmentType data ' "+id+" ' save";
		//msg send to UI
		model.addAttribute("message", msg);
		//to hide data form backing object without data
		model.addAttribute("shipmentType", new ShipmentType());
		return "ShipmentTypeRegister";
	}
    @GetMapping("/all")
    public String showAll(Model model)
    {
    	List<ShipmentType> list=service.getAllShipmentType();
    	model.addAttribute("list", list);
		return "ShipmentTypeData";
    }
    
    @GetMapping("/delete/{id}")
    public String removeById(@PathVariable Integer id,Model model)
    {
    	String msg=null;
    	if(service.isShipmentTypeExist(id))
    	{
    		service.deleteShipmentType(id);
    		msg="ShipmentType ' "+id+ " 'deleted";
    	}
    	else {
    		msg="ShipmentType ' "+id+ " 'not Exist";
    	}
    	model.addAttribute("message", msg);
    	//fetch other data
    	List<ShipmentType> list=service.getAllShipmentType();
    	model.addAttribute("list", list);
    	return "ShipmentTypeData";
    }
	//onclick edit hyperlink on ui
    //read one path variable and fetch data from service if exist send to edit page else redirect to data page
    //show edit page with data
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id,Model model)
    {
    	Optional<ShipmentType> opt=service.getOneShipmentType(id);
    	String page=null;
		if(opt.isPresent())
    	{
    		ShipmentType st=opt.get();
    		model.addAttribute("shipmentType", st);
    		page="ShipmentTypeEdit";
    	}
    	else
    		page="redirect:../all";
		return page;
    }
    //onclik update button,read form data and perform update operation send back to data page success msg
    @PostMapping("/update")
    public String update(@ModelAttribute ShipmentType shipmentType,Model model)
    {
    	service.updateShipmentType(shipmentType);
    	String msg="ShipmentType ' "+shipmentType.getId()+" ' Updated";
    	model.addAttribute("message", msg);
    	//show new data other rows
    	List<ShipmentType> list=service.getAllShipmentType();
    	//sending form backing object with data
    	model.addAttribute("list", list);
		return "ShipmentTypeData";
    	
    }
    
    // view the page
    @GetMapping("/view/{id}")
    public String view(@PathVariable Integer id,Model model) {
    	String page=null;
		Optional<ShipmentType>opt=service.getOneShipmentType(id);
		if(opt.isPresent()) {
			ShipmentType om=opt.get();
			model.addAttribute("ob",om);
			page="ShipmentTypeView";
		}
		else {
			return "redirect:../all";
		}
		return page;
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
