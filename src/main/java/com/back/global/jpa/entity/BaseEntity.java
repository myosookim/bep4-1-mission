package com.back.global.jpa.entity;

import com.back.global.global.GlobalConfig;
import com.back.standard.modelType.CanGetModelTypeCode;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity implements CanGetModelTypeCode {
    public abstract int getId();
    public abstract LocalDateTime getCreateDate();
    public abstract LocalDateTime getModifyDate();

    public String getModelTypeCode(){
        return this.getClass().getSimpleName();
    }

    protected void publishEvent(Object event){
        GlobalConfig.getEventPublisher().publish(event);
    }
}
