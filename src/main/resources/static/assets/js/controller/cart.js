import { orderlineService } from '../service/orderlineService.js';
import { headerService } from '../common/header.js';

const orderLines = document.getElementsByClassName('order-line');

// Colocando funcionalidades nos botões de cada orderline
for(let orderline of orderLines){

    console.log(orderline)

    const orderlineComponent = {
        productId: orderline.getAttribute("data-product-id"),
        btnRemover: orderline.querySelector('.btn-remove-product'),
        btnDecrement: orderline.querySelector('.decrement'),
        btnIncrement: orderline.querySelector('.increment'),
        quantityProduct: orderline.querySelector('.quantity-product'),
        totalValueOrderline: orderline.querySelector('.value-orderline')
    }

    const orderlineDTO = {
        productId: orderlineComponent.productId,
        quantity: orderlineComponent.quantityProduct.value
    }

    orderlineComponent.btnRemover.addEventListener('click', async () => {
        await orderlineService.removeOrderline(orderlineComponent.productId).then((isOk) => {
            if (isOk)
                removeDivProduct(orderlineComponent.productId);

            updateOrderSummaryComponents(orderlineComponent);
            headerService.updateCart();
        })
    });

    orderlineComponent.btnDecrement.addEventListener('click', async () => {
        orderlineDTO.quantity--;
        await orderlineService.updateQuantityOrderline(orderlineDTO).then((isOk)=>{
            if(isOk){
                orderlineComponent.quantityProduct.value--;
                if(orderlineComponent.quantityProduct.value == 0)
                    removeDivProduct(orderlineComponent.productId);
                
                headerService.updateCart();
                updateOrderSummaryComponents(orderlineComponent);
            }
        });               
    });

    orderlineComponent.btnIncrement.addEventListener('click', async () => {
        orderlineDTO.quantity++;
        await orderlineService.updateQuantityOrderline(orderlineDTO).then((isOk)=>{
            if(isOk){
                orderlineComponent.quantityProduct.value++;
                headerService.updateCart();
                updateOrderSummaryComponents(orderlineComponent);
            }
        });                 
    });
    
}

function removeDivProduct(productId){
    let divProduct = document.getElementById(`card-product-${productId}`);
    divProduct.style.display = 'none';
}

async function updateOrderSummaryComponents(orderlineComponent){
    const cart = await orderlineService.getCart();

    cart.orderLines.forEach((order) => {
        if(order.product.id == orderlineComponent.productId){
            //Atualizando o valor da orderline
            orderlineComponent.totalValueOrderline.textContent = `R$${order.purchasePrice.toFixed(2)}`;
            return; //Interromper o loop
        }
    });

    // Atualizando elementos do carrinho(cart)
    document.getElementById('total-price-products').textContent = `R$${cart.totalValue.toFixed(2)}`;
    document.getElementById('total-cart-value').textContent = `R$${cart.totalValue.toFixed(2)}`;
}


//btnsDecrement();
//btnsIncrement();
//btnsRemoveOrderLine();
