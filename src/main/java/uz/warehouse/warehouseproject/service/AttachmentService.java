package uz.warehouse.warehouseproject.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.warehouse.warehouseproject.entity.Attachment;
import uz.warehouse.warehouseproject.entity.AttachmentContent;
import uz.warehouse.warehouseproject.payload.Message;
import uz.warehouse.warehouseproject.repository.AttachmentContentRepository;
import uz.warehouse.warehouseproject.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;

@Service
public class AttachmentService {
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }

    public Message uploadFileService(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        Attachment savedAttachment = null;
        while (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            assert file != null;
            Attachment attachment = new Attachment(file.getOriginalFilename(), file.getSize(), file.getContentType());
            savedAttachment = attachmentRepository.save(attachment);
            try {
                AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), savedAttachment);
                attachmentContentRepository.save(attachmentContent);
            } catch (IOException e) {
                return new Message(false, "This photo is invalid!");
            }
        }
        assert savedAttachment != null;
        return new Message(true, "This photo has been successfully saved!", savedAttachment.getId());
    }
}
