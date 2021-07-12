package in.nit.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import in.nit.model.ShipmentType;
import in.nit.service.IShipmentTypeService;

@Controller
@RequestMapping("/shipmenttpye")
public class ShipmentTypeController {
	@Autowired
	private IShipmentTypeService service;

	private Logger log=LoggerFactory.getLogger(ShipmentTypeController.class);

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
		log.info("Enter into save method");
		try {
			Integer id=service.saveShipmentType(shipmentType);
			String msg="ShipmentType data ' "+id+" ' saved successfully";

			log.debug(msg);

			//msg send to UI
			model.addAttribute("message", msg);
			//to hide data form backing object without data
			model.addAttribute("shipmentType", new ShipmentType());
		} 
		catch (Exception e) {
			log.error("Unable to save:"+e.getMessage());
			e.printStackTrace();
		}

		log.info("Back to UI Page");

		return "ShipmentTypeRegister";
	}

	@GetMapping("/all")
	public String showAll(Model model)
	{
		try {
			log.info("Entered into info");
			List<ShipmentType> list=service.getAllShipmentType();
			model.addAttribute("list", list);
			log.info("Got Data from DB using service:size of List is:"+list.size());

		} catch (Exception e) {
			log.error("Unable to fetch data from DB"+e.getMessage());
			e.printStackTrace();
		}
		log.info("Back to UI page");
		return "ShipmentTypeData";
	}

	@GetMapping("/delete/{id}")
	public String removeById(@PathVariable Integer id,Model model)
	{
		String msg=null;
		log.info("Enter into delete with Id"+id);
		try {
			if(service.isShipmentTypeExist(id))
			{
				service.deleteShipmentType(id);
				msg="ShipmentType ' "+id+ " 'deleted";
				log.debug(msg); //debug provide success msg
			}
			else {
				msg="ShipmentType ' "+id+ " 'not Exist";
				log.warn(msg);
			}
			model.addAttribute("message", msg);

			log.info("Fetching new data after delete" );

			//fetch other data
			List<ShipmentType> list=service.getAllShipmentType();
			model.addAttribute("list", list);


		} catch (Exception e) {
			log.error("Unable to perform delete operation"+e.getMessage());

			e.printStackTrace();
		}
		log.info("Back to UI page");
		return "ShipmentTypeData";
	}

	//onclick edit hyperlink on ui
	//read one path variable and fetch data from service if exist send to edit page else redirect to data page
	//show edit page with data
	@GetMapping("/edit/{id}")
	public String showEdit(@PathVariable Integer id,Model model)
	{
		log.info("Enter into edit operation with id"+id);
		String page=null;
		try {
			Optional<ShipmentType> opt=service.getOneShipmentType(id);


			log.info("Service called is made");

			if(opt.isPresent())
			{
				log.info("Data exist with Id"+id);

				ShipmentType st=opt.get();
				model.addAttribute("shipmentType", st);
				page="ShipmentTypeEdit";
			}
			else
			{
				log.warn("Data not exist with Id"+id);
				page="redirect:../all";
			}
		} catch (Exception e) {
			log.error("Unable to perform Edit operation"+e.getMessage());
			e.printStackTrace();

		}
		log.info("Back to UI page");

		return page;
	}


	//onclik update button,read form data and perform update operation send back to data page success msg
	@PostMapping("/update")
	public String update(@ModelAttribute ShipmentType shipmentType,Model model)
	{
		log.info("Enter into update operation ");
		
		try {
			service.updateShipmentType(shipmentType);
			String msg="ShipmentType ' "+shipmentType.getId()+" ' Updated";
			
			log.debug(msg);
			
			model.addAttribute("message", msg);
			
			log.info("Fetching latest data");
			
			//show new data other rows
			List<ShipmentType> list=service.getAllShipmentType();
			//sending form backing object with data
			model.addAttribute("list", list);
		} catch (Exception e) {
			log.error("Unable to perform update operation"+e.getMessage());
			e.printStackTrace();
		}
		
		log.info("Back to UI page");
		return "ShipmentTypeData";

	}

	// view the page
	@GetMapping("/view/{id}")
	public String view(@PathVariable Integer id,Model model) {
		String page=null;
		log.info("Enter into view operation with id "+id);
		
		try {
			
			Optional<ShipmentType>opt=service.getOneShipmentType(id);
			log.info("Service called is made");
			
			if(opt.isPresent()) {
				log.info("Data exist with Id"+id);
				
				ShipmentType om=opt.get();
				model.addAttribute("ob",om);
				page="ShipmentTypeView";
			}
			else {
				log.warn("Data not exist with Id"+id);
				return "redirect:../all";
			}
		} 
		catch (Exception e) {
			
		}
		
		
		log.info("Back to UI page");
		return page;


	}


	//Ajax validations

	@GetMapping("/validatecode")
	public @ResponseBody String validateShipmentCode(@RequestParam  String code) {

		String message="";

		if(service.isShipmentTypeCodeExist(code)) {
			message="<b>ShipmentType Code ' "+code+" ' already exist !</b>"; 
		}
		return message;

	}






















	//export data to excel file

	/*    @GetMapping("/excel")
     public ModelAndView  exportToFile() {
    	ModelAndView  m= new ModelAndView();
    	 m.setView(new ShipmentTypeExcelView());

    	 //send data to view class
    	 List<ShipmentType>list=service.getAllShipmentType();
    	 m.addObject("ob",list);

    	 return m;

     }
	 */



















}
