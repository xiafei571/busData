/**
 * 初始化分页信息
 * 
 * @param pagation
 * @returns
 */
function initPage(pageIndex, pageSize, totalCountPage) {
	// 如果当前页是最后一页的话，下一页置灰
	if (pageIndex == totalCountPage) {
		$("#page_next").addClass("disabled");
	} else {
		$("#page_next").removeClass("disabled");
	}

	// 如果当前页是第一页的话，上一页置灰
	if (pageIndex == 1) {
		$("#page_pre").addClass("disabled");
	} else {
		$("#page_pre").removeClass("disabled");
	}

	var page_num = "";
	var i = 1;
	var j = totalCountPage;

	if (pageIndex > 10) {
		i = pageIndex - 9;
	}

	if (Number(pageIndex) + 9 < totalCountPage) {
		$("#page_next").before("...");
		j = Number(pageIndex) + 10;
	}

	for (; i < j; i++) {
		if (pageIndex == i) {
			page_num = page_num
					+ ' <li class="page-item active"><a class="page-link" href="#">'
					+ i + '<span class="sr-only">(current)</span></a></li>';
		} else {
			page_num = page_num
					+ '<li class="page-item"><a class="page-link" href="list?pageIndex='
					+ i + '&pageSize=' + pageSize + '">' + i + '</a></li>';
		}
	}
	
	$("#page_pre").after(page_num);
	
	if (pageIndex > 10) {
		$("#page_pre").after("...");
	}
}