package uz.warehouse.warehouseproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.warehouse.warehouseproject.entity.Attachment;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
