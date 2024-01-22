package jp.ken.jdbc.group;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence(value = {Default.class,Group1.class})
public interface GroupOrder {}
