package in.nit.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

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

import in.nit.model.PurchaseOrder;
import in.nit.service.IPurchaseOrderService;
import in.nit.service.IShipmentTypeService;

@Controller
@RequestMapping("/purchaseorder")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService service;
	
	@Autowired
	private IShipmentTypeService shipmentTypeservice;

	@Autowired
	private ServletContext context;
	
	
	//call this method inside other controller methods
		// where return page is Register or Edit.
	//"shipmentTypes" is a has-a variable name
		private void addDorpDownUi(Model model) {
			model.addAttribute("shipmentTypes",shipmentTypeservice.getShipmentIdAndCode());
			//model.addAttribute("whUserTypes",whUserTypeService.getWhUserTypeIdAndCode("Vendor"));
		}

	// 1. Show Register Page
		@GetMapping("/register")
		public String showRegister(Model model) {

			model.addAttribute("purchaseOrder", new PurchaseOrder());
			addDorpDownUi(model);
			return "PurchaseOrderRegister";
		}

		// 2. save : on click submit

		@PostMapping("/save")
		public String save(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {

			Integer id = null;
			String message = null;
			// peform save operation
			id = service.savePurchaseOrder(purchaseOrder);
			// construct one message
			message = "purchase Order '" + id + "' saved successfully";
			// send message to UI
			model.addAttribute("message", message);

			model.addAttribute("purchaseOrder", new PurchaseOrder());
			
			addDorpDownUi(model);
			
			// got to Page
			return "PurchaseOrderRegister";
		}
		
		
		// 3.Displaying data:
		@GetMapping("/all")
		public String fetchAll(Model model) {
			List<PurchaseOrder> list = service.getAllPurchaseOrders();
			model.addAttribute("list", list);
			return "PurchaseOrderData";
		}

		
		// 4.delete record
		@GetMapping("/delete/{id}")
		public String remove(@PathVariable Integer id, Model model) {
			String msg = null;
			// invoke service
			if (service.isPurchaseOrderExist(id)) {
				service.deletePurchaseOrder(id);
				msg = "Purchase Order '" + id + "' Type deleted !";
			} else {

				msg = "Purchase Order'" + id + "' Not Existed !";
			}
			// display other records
			List<PurchaseOrder> list = service.getAllPurchaseOrders();
			model.addAttribute("list", list);

			// send confirmation to UI
			model.addAttribute("message", msg);
			return "PurchaseOrderData";
		}

		
		// 5.Edit form
		@GetMapping("/edit/{id}")
		public String showEdit(@PathVariable Integer id, Model model) {
			String page = null;
			Optional<PurchaseOrder> opt = service.getOnePurchaseOrder(id);
			if (opt.isPresent()) {
				PurchaseOrder order = opt.get();			
				model.addAttribute("purchaseOrder", order);
				addDorpDownUi(model);
				page = "PurchaseOrderEdit";
			} else {
				page = "redirect:../all";
			}
			return page;
		}
		
		// 6.update method
		@PostMapping("/update")
		public String update(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {
			String msg = null;
			service.updatePurchaseOrder(purchaseOrder);
			msg = "Purchase Order '" + purchaseOrder.getId() + "' updated successfully..";
			model.addAttribute("message", msg);

			// display other records
			List<PurchaseOrder> list = service.getAllPurchaseOrders();
			model.addAttribute("list", list);
			return "PurchaseOrderData";
		}
		
		
		// 7.show One
		@GetMapping("/view/{id}")
		public String showView(@PathVariable Integer id, Model model) {
			String page = null;
			Optional<PurchaseOrder> opt = service.getOnePurchaseOrder(id);
			if (opt.isPresent()) {
				PurchaseOrder order = opt.get();
				model.addAttribute("ob", order);
				page = "PurchaseOrderView";
			} else {
				page = "redirect:../all";
			}
			return page;
		}

		// 12. ---AJAX VALIDATION----------
		// .. /shipmenttype/validatecode?code=ABCD
		@GetMapping("/validatecode")
		public @ResponseBody String validatePurchaseOrderCode(@RequestParam String code) {
			String message = "";
			if (service.isPurchaseOrderCodeExist(code)) {
				message = "Purchase Order Code <b>'" + code + "' Already exist</b>!";
			}
			return message;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
}
