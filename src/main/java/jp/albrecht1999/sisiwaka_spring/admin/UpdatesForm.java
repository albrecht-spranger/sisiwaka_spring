package jp.albrecht1999.sisiwaka_spring.admin;

import java.util.ArrayList;
import java.util.List;

public class UpdatesForm {
    private List<AdminUpdateFormDto> rows = new ArrayList<>();

    public List<AdminUpdateFormDto> getRows() {
        return rows;
    }

    public void setRows(List<AdminUpdateFormDto> rows) {
        this.rows = rows;
    }
}
