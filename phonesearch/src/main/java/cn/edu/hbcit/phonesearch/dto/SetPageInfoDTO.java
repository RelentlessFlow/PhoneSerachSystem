package cn.edu.hbcit.phonesearch.dto;


public class SetPageInfoDTO {
    private int pageSize;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public SetPageInfoDTO(int pageSize, int pageNum) {
        this.pageSize = pageSize;
        this.pageNum = pageNum;
    }

    private int pageNum;
}
