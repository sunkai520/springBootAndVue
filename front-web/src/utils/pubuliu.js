/**
 * 此方法要求固定dom结构以及class名字否则有些值会取不到
 * @param {*} initDom 
 * @returns 
 */
//获取瀑布流内容的宽度高度,用于计算每个块的宽度以及高度
function getClient(initDom) {
    return {
        with: initDom.innerWidth || initDom.clientWidth || initDom.clientWidth,
        height: initDom.innerHeight || initDom.clientHeight || initDom.clientHeight,
    };
}
//进行dom重排
export function waterFall(initDom,culumns) {
    let pageWidth = getClient(initDom).with - 25;//25为padding
    let columns = culumns;
    let itemWidth = parseInt((pageWidth - (20+(columns-1)*20)) / columns); //根据几列获取每个块的宽度20为块于块的间距计算时需要抛出
    let items = document.getElementsByClassName("item");//获取总共有多少块[item,item,item]
    let arr = [];
    for (let i = 0; i < items.length; i++) {
        let dom = items[i];
        let cc = dom.querySelector(".ct");//获取块元素,块结构<div class="item"><div class="ct"><div class="title"></div><img/><div class="des"></div></div></div></div>
        let img = cc.querySelector("img");//获取块元素中的图片
        console.log(img.height,"图片的高度为:")
        if (img.height == 0) { //因为图片是地址,可能有时候图片没有加载完导致高度拿不到,所以解决办法是监听imgload完后再调用
            return
        }
        dom.style = "width:" + itemWidth + "px;"; //给每个块设置宽度
        let imgHeight =
            dom.querySelector(".ct").clientHeight ||
            dom.querySelector(".ct").height;
        if (i < columns) { //第一列
            let lft = itemWidth * i + 20 * i;
            dom.setAttribute(
                "style",
                dom.getAttribute("style") + "top:" + 0 + "px;left:" + lft + "px"
            );
            arr.push(imgHeight); //记录第一列每个item的高度,因为是瀑布流模式高度肯定不统一
        } else {
            let minHeight = arr[0]; //设置最小高度默认第一个
            let index = 0;
            for (let j = 0; j < arr.length; j++) { //遍历arr找出最小高度的是第几列,并记录是第几个
                if (minHeight > arr[j]) {
                    minHeight = arr[j]; //记录最小高度的值
                    index = j;//记录最小高度为第几个index
                }
            }
            let top = arr[index] + 30; //计算出第二行的块放在 最小高度块的后面并且上下间距为30
            let lt = items[index].style.left; //距离左边的距离和最小高度块一样
            dom.setAttribute( //给当前块设置left,top
                "style",
                dom.getAttribute("style") + "top:" + top + "px;left:" + lt
            );
            //精髓就是arr数组,他的长度永远等于列数,记住每一列的高度,并且每次插入块时,都是插入最小高度那列,循环如此即使瀑布流.
            arr[index] = arr[index] + imgHeight + 30; //给最小高度的值累加高度,下一次计算可能就不是他是最小高度了
        }
    }
}