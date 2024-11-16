import { routes } from "./routes.js";

async function getAddress(zipCode){

    const response = await fetch(`${routes.address}/${zipCode}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).catch((erro) => {
        console.error(`Erro ao consultar cep. ${erro}`);
    })

    if(!response.ok){
        console.error("Status método fora de 200");
    }

    const addressJson = await response.json();

    return addressJson;
}

export const addressService = {
    getAddress,
}