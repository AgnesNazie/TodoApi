package se.lexicon.todoapi.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.todoapi.entity.Attachment;

public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
}
