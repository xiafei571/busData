package common.page;

public class Pagination {
	// 1. 第几页
	private Integer pageIndex = 1;
	// 2. 一页多少个
	private Integer pageSize = 10;
	// 3. 一共多少条数据
	private Integer totalCount;
	// 4. 一共多少页 [5+(3-1）] / 3 = 2
	private Integer totalCountPage;
	// 5. 当前页数量 list.size
	private Integer currentPageCount;
	// 6. 游标开始 (pageIndex-1)*pageSize
	private Integer cursor = null;
	// 7. 游标数量
	private Integer offset;

	public Pagination(Integer pageIndex, Integer pageSize) {
		if (null == pageIndex || null == pageSize) {
			this.pageIndex = 1;
			this.pageSize = 10;
		} else {
			this.pageIndex = pageIndex;
			this.pageSize = pageSize;
		}
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalCountPage() {
		// totalCountPage = (totalCount+pageSize-1)/pageSize
		if (null == totalCountPage) {
			this.totalCountPage = (totalCount + pageSize - 1) / pageSize;
		}
		return this.totalCountPage;
	}

	public void setTotalCountPage(Integer totalCountPage) {
		this.totalCountPage = totalCountPage;
	}

	public Integer getCurrentPageCount() {
		return currentPageCount;
	}

	public void setCurrentPageCount(Integer currentPageCount) {
		this.currentPageCount = currentPageCount;
	}

	public Integer getCursor() {
		// (pageIndex-1)*pageSize
		if (null == cursor)
			return (pageIndex - 1) * pageSize;
		return cursor;
	}

	public void setCursor(Integer cursor) {
		this.cursor = cursor;
	}

	public Integer getOffset() {
		if (null == offset)
			offset = pageSize;
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

}
