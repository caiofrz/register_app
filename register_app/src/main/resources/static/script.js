const form = document.querySelector("form");

const name = document.getElementById("name");
const email = document.getElementById("email");
const password = document.getElementById("password");
const phone = document.getElementById("phone");

function signup(data) {
    fetch("http://127.0.0.1:8080/cadad", {
        headers:{
            'Accept': 'application/json',
            'Content-type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(data)
    })
    .then(() => console.log("Cadastrado!"))
    .catch(() => console.log("Falha ao cadastrar!"));
};

function clear(){
    name.value = "";
    email.value = "";
    password.value = "";
    phone.value = "";
};


form.addEventListener("submit", (event) => {
  event.preventDefault();

  const data = {
    name: name.value,
    email: email.value,
    password: password.value,
    phone: phone.value
  };
  console.log(JSON.stringify(data));

  signup();
  clear();

});


