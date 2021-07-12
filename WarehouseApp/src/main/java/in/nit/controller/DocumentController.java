package in.nit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.nit.model.Document;
import in.nit.service.IDocumentService;

@Controller
@RequestMapping("/documents")
public class DocumentController {

	@Autowired
	private IDocumentService service;

	@GetMapping("/all")
	public String showDocs() {
		
		
		return "Documents";
	}

	@PostMapping("/save")
	public String upload(@RequestParam Integer fileId,
			@RequestParam MultipartFile fileOb) 
	{
		Document doc=new Document();
		if(fileOb!=null && fileId!=null)
		{
			doc.setDocId(fileId);
			doc.setDocName(fileOb.getOriginalFilename());

			try {
				doc.setDocData(fileOb.getBytes());
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		service.saveDocument(doc);

		return "Documents";

	}

}
