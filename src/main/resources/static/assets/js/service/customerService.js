import { routes } from "./routes.js";

async function create(customerDTO){
    const response = await fetch(`${routes.customers}`,{
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        body: JSON.stringify(customerDTO)
    });

    if(!response.ok){
        const body = await response.json();
        throw Error(body.message);
    }

    return response.ok; // True se o status for entre 200 e 299
}

async function getCustomerRegister(email){
    const response = await fetch(`${routes.customers}?email=${email}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    });

    if(!response.ok){
        const body = await response.json();
        throw Error(body.message);
    }
}

async function login(login){
    const data = new URLSearchParams();
    data.append('email', login.email);  
    data.append('password', login.password);

    const response = await fetch(`${routes.auth}/login`,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: data.toString()
    })

    if (!response.ok) {
        const body = await response.json();
        throw Error(body.message);
    }

    const body = await response.json();

    const status = response.ok;
    const url = body.url;

    console.log(`status: ${status} | url: ${url}`);

    return { status, url };
}

export const customerService = {
    create,
    login
}