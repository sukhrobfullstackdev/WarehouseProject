package uz.warehouse.warehouseproject.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.service.AttachmentService;

@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {
    final AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping(value = "/upload")
    public Message uploadFileController(MultipartHttpServletRequest request) {
        return attachmentService.uploadFileService(request);
    }
}
