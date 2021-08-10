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

import in.nit.model.Part;
import in.nit.service.IPartService;
import in.nit.service.IUOMService;

@Controller
@RequestMapping("/parts")
public class PartController {

	@Autowired
	private IPartService service;
	
	@Autowired
	private IUOMService uomService;
	
	
	//call this method inside other controller method 
	//where return page is Register or edit
	private void addDropdownUI(Model model){

		model.addAttribute("uoms",uomService.getUomIdAndModel());
	}
	
	
	// 1. show register page
		@GetMapping("/register")
		public String showRegister(Model model) {
			//form backing obj
			model.addAttribute("part",new Part());
			
			addDropdownUI(model);
			return "PartRegister";
		}

		//2.save data
		@PostMapping("/save")
		public String save(@ModelAttribute Part part,Model model) {

			Integer id=service.savePart(part);
			String message="Part ' "+id+" ' saved";
			model.addAttribute("message", message);

			//to clear form data and show again register page
			model.addAttribute("part",new Part());
			
			addDropdownUI(model);
			return "PartRegister";
		}
	
		
		//3. show all 
		@GetMapping("/all")
		public String showAll(Model model) {
			model.addAttribute("list",service.getAllPart());
			return "PartData";
		}

		//4. show edit page
		@GetMapping("/edit/{id}")
		public String showEdit(@PathVariable Integer id,Model model) {
			String page=null;
			Optional<Part> om=service.getOnePart(id);
			if(om.isPresent()) {
				//if id is present then getting that object from controller to UI
				model.addAttribute("part", om.get());
				
				addDropdownUI(model);
				
				page="PartEdit";
			}
			else {
				return "redirect:../all";
			}

			return page;
		}
		
		
		// do upadate
		@PostMapping("/update")
		public String update(@ModelAttribute Part part,Model model ) {
			service.updatePart(part);
			//sending message 
			model.addAttribute("message","Part '"+part.getId()+"' Updated!" );

			//sending list also
			model.addAttribute("list",service.getAllPart());

			return "PartData";
		}
		
		//5 delete
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable Integer id,Model model){
			String msg=null;
			if(service.isPartExist(id)) {
				service.deletePart(id);;
				msg="Part '"+id+"' Deleted!";
			}
			else {
				msg="Part '"+id+"' not existed!";
			}
			
			model.addAttribute("msg",msg);
			model.addAttribute("list",service.getAllPart());
			
			return "PartData";
			
		}
		
		
		//6 show view page
		@GetMapping("/view/{id}")
		public String showView(@PathVariable Integer id,Model model) {
			String page=null;
			Optional<Part>opt=service.getOnePart(id);
			if(opt.isPresent()) {
				Part om=opt.get();
				model.addAttribute("ob",om);
				page="PartView";
			}
			else {
				return "redirect:../all";
			}
			return page;
		}
		
		// 12.Ajax Validation
		@GetMapping("/validatecode")
		public @ResponseBody String validatePartCode(
				@RequestParam String code,
				@RequestParam Integer id)
		{
			String msg = "";
			if (id==0 && service.isPartCodeExist(code)) { //register
				msg = "Part Code <b>'" + code + "' is Already exist</b>!";
			}else if (service.isPartCodeExistForEdit(code,id)) { //edit
				msg = "Part Code <b>'" + code + "' is Already exist</b>!";
			} 
			return msg;
		}
		
		
		
		
		
	
}
