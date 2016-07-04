package tk.jingzing.dubbo.service;

import tk.jingzing.dubbo.bean.Note;

import java.util.List;

/**
 * Created by Louis Wang on 2016/7/4.
 */

public interface NoteService {
    /* 根据主键 */
    Note queryById(String i);

    /**
     * @Description:
     * @see:例如 select * from tcnote where note_name="文本名称" and author_name="高广金"
     * @param note
     * @return:List<Note>
     */
    List<Note> queryParamAnd(Note note);
}
