package com.fc.vo;

import com.fc.entity.TbNote;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NoteVO extends TbNote {
    private Integer typeId;
    private String groupName;
    private Integer noteCount;
}
