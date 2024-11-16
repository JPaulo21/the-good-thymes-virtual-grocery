import { routes } from "./routes.js";

async function getCart(){
    const response = await fetch(`${routes.cart}/session`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).catch((erro) => {
        console.error(`Erro ao consultar o carrinho! ${erro}`);
    });

    return response.json();
}

async function addOrderline(orderline){
    const response = await fetch(`${routes.cart}/add`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderline)
    }).catch(erro => {
        console.error('Error adicionar recurso');
    });

   return response.ok;
}

async function getOrderlineById(url){ 
    const response = await fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    }).catch(erro => {
        console.error('Error ao realizar consulta');
    });

    if(response.ok)
        return response.json();
}

async function updateQuantityOrderline(orderlineDTO){
    const response = await fetch(`${routes.cart}/product`, {
        method: 'PATCH',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderlineDTO)
    });
    
    return response.ok; // true para status code entre 200 e 299
}

async function removeOrderline(productId){
    const response = await fetch(`${routes.cart}/product/${productId}`, {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });

    return response.ok;
}

export const orderlineService = {
    getCart,
    addOrderline,
    getOrderlineById,
    updateQuantityOrderline,
    removeOrderline
}