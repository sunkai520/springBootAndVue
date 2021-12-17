import { reactive} from "@vue/reactivity";
/**
 * 分页公共处理js
 * @param {*} callBack 函数回调 
 * @param {*} obj 分页默认参数
 * @returns 
 */
export default function (callBack,obj) {
    let defaultObj = obj||{
        list_rows: 10,
        page: 1,
        total: 0,
    }
    let pageParams =reactive({
        ...defaultObj
    })
    let handlePageChange = (page) => {
        pageParams.page = page
        callBack && typeof callBack == "function" && callBack();
    }
    let handleSizeChange = (size) => {
        pageParams.list_rows = size;
        pageParams.page = 1;
        callBack && typeof callBack == "function" && callBack();
    }
    return {
        pageParams,
        handlePageChange,
        handleSizeChange
    }
}