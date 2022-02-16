/**
 * 此方法要求固定dom结构以及class名字否则有些值会取不到
 * @param {*} initDom 
 * @returns 
 */
function getClient(initDom) {
    return {
        with: initDom.innerWidth || initDom.clientWidth || initDom.clientWidth,
        height: initDom.innerHeight || initDom.clientHeight || initDom.clientHeight,
    };
}
export function waterFall(initDom,culumns) {
    let pageWidth = getClient(initDom).with - 25;
    let columns = culumns;
    let itemWidth = parseInt((pageWidth - (20+(columns-1)*20)) / columns);
    let items = document.getElementsByClassName("item");
    let arr = [];
    for (let i = 0; i < items.length; i++) {
        let dom = items[i];
        let cc = dom.querySelector(".ct");
        let img = cc.querySelector("img");
        console.log(img.height,"图片的高度为:")
        if (img.height == 0) {
            return
        }
        dom.style = "width:" + itemWidth + "px;";

        let imgHeight =
            dom.querySelector(".ct").clientHeight ||
            dom.querySelector(".ct").height;
        console.log(imgHeight, "index:" + i);
        if (i < columns) {
            let lft = itemWidth * i + 20 * i;
            dom.setAttribute(
                "style",
                dom.getAttribute("style") + "top:" + 0 + "px;left:" + lft + "px"
            );
            arr.push(imgHeight);
        } else {
            let minHeight = arr[0];
            let index = 0;
            for (let j = 0; j < arr.length; j++) {
                if (minHeight > arr[j]) {
                    minHeight = arr[j];
                    index = j;
                }
            }
            let top = arr[index] + 30;
            let lt = items[index].style.left;
            dom.setAttribute(
                "style",
                dom.getAttribute("style") + "top:" + top + "px;left:" + lt
            );
            arr[index] = arr[index] + imgHeight + 30;
        }
    }
}