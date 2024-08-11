package com.prototype.personal_manager.entities;

import com.prototype.personal_manager.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class Files extends BaseEntity {
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "facilities_id")
    private Facility facility;
}
