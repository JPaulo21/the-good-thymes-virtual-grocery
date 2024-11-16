import { orderlineService } from "../service/orderlineService.js"; 

const products = document.getElementsByClassName('product');

for(const product of products){

    const productId = product.getAttribute("data-product-id");
    const img = product.getElementsByTagName("img");
    const titleCard = product.getElementsByTagName("h5");
    const btnAddCart = product.querySelector('.add');

    btnAddCart.addEventListener('click', async () => {
        const orderlineData = {
            productId: productId,
            quantity: 1 
        }
    
        await orderlineService.addOrderline(orderlineData).then((isOk) => {
            if(isOk){
                window.location.href = `/cart`;
            }
        });
    })

}


