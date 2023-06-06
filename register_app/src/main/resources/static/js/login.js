const form = document.querySelector("form");

const email = document.getElementById("email");
const password = document.getElementById("password");

function signin(data) {
    fetch("http://127.0.0.1:8080/user/login", {
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
    email.value = "";
    password.value = "";
};


form.addEventListener("submit", (event) => {
  event.preventDefault();

  const data = {
    email: email.value,
    password: password.value,
  };

  signin(data);
  clear();
});
