export default function(){
       //用于验证此集合是否是全部选中
       function validateChecked(items, bool) {
        if (items.checkedAll && !bool) {
          items.checkedAll = bool;
        }
        if (bool) {
          let b = true;
          items.childrens.forEach((i) => {
            if (!i.checked) {
              b = false;
            }
          });
          items.checkedAll = b;
        }
      }
  
      //选中设置值
      function setValue(item, k) {
        item.checked = !item.checked;
        if (item.childrens) {
          item.childrens.forEach((subItem) => {
            subItem.checked = item.checked;
          });
        }
        if (k) {
          validateChecked(k, item.checked);
        }
      }
      //全选函数设置值
      function setAllValue(item) {
        item.checkedAll = !item.checkedAll;
        item.childrens.forEach((i) => {
          i.checked = item.checkedAll;
        });
      }
      return {
        setAllValue,
        setValue
      }
}