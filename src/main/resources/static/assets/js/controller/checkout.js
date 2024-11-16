import { addressService } from '../service/addressService.js';

const addressComponent = {
    zipCode: document.getElementById('zipCode'),
    address: document.getElementById('address'),
    city: document.getElementById('city'),
    state: document.getElementById('state')
}

document.getElementById('zipCode').addEventListener('focusout', async (event) => {
    const zipCode = event.target.value.replace(/\D/g, "");

    if(!zipCode)
        throw new Error('zipCode is null');

    addressService.getAddress(zipCode).then((address) => {
        console.log(`Adress: ${address}`); 
        console.log(address); 

        addressComponent.address.value = address.logradouro;
        addressComponent.city.value = address.localidade;
        addressComponent.state.value = address.estado;
    });
});