package nl.benooms.enverspringdataplayground.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * author Ben Ooms
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AbstractAuditedEntity {
	@CreatedBy
	String CreatedByUser;
	@CreatedDate
	LocalDateTime createdDate;
	@LastModifiedBy
	String lastModifiedByUser;
	@LastModifiedDate
	LocalDateTime lastModifiedDate;
}
