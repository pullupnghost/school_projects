const loginForm = document.querySelector("#loginForm");
const registerForm = document.querySelector("#registerForm");
const titulo_form = document.querySelector("#titulo_form");

const goToRegister = document.querySelector("#goToRegister");
const goToLogin = document.querySelector("#goToLogin");

goToLogin.addEventListener("click", () => {
  loginForm.style.display = "block";
  registerForm.style.display = "none";
  titulo_form.textContent = "Login";
});

goToRegister.addEventListener("click", () => {
  loginForm.style.display = "none";
  registerForm.style.display = "block";
  titulo_form.textContent = "Registar";
});

function exibirAlerta(mensagem) {
  const alertDiv = document.createElement("div");
  alertDiv.className = "alert";
  alertDiv.textContent = mensagem;

  document.body.appendChild(alertDiv);

  setTimeout(() => {
    alertDiv.style.opacity = "0";
    setTimeout(() => {
      alertDiv.remove();
    }, 1000);
  }, 3000);
}
