package jp.albrecht1999.sisiwaka_spring.admin;

import java.util.ArrayList;
import java.util.List;

public class UpdatesForm {
    private List<UpdateRowDto> rows = new ArrayList<>();

    public List<UpdateRowDto> getRows() {
        return rows;
    }

    public void setRows(List<UpdateRowDto> rows) {
        this.rows = rows;
    }
}
