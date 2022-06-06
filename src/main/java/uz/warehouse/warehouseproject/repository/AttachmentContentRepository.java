package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.AttachmentContent;
@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent,Integer> {
}
