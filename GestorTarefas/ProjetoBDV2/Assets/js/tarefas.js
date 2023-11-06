//MODAL ADD CATEGORIA
const addCategoriaBtn = document.querySelector("#add-categoria");
const categoriaForm = document.querySelector("#categoria-form");
const addCategoriaModal = document.querySelector("#add-categoria-modal");
const closeModalBtn = document.querySelector(".close");

function fecharModal() {
  var modal = document.getElementById('add-categoria-modal');
  addCategoriaModal.style.display = 'none';
}

addCategoriaBtn.addEventListener("click", () => {
  categoriaForm.style.display = "block";
});

addCategoriaBtn.addEventListener("click", () => {
  addCategoriaModal.style.display = "block";
});

closeModalBtn.addEventListener("click", () => {
  addCategoriaModal.style.display = "none";
});

window.addEventListener("click", (event) => {
  if (event.target === addCategoriaModal) {
    addCategoriaModal.style.display = "none";
  }
});

// DATA LIMITER
const now = new Date().toISOString().slice(0, 16);

const data_criado = document.querySelector('#data_criado');
const data_vencimento = document.querySelector('#data_vencimento');

data_criado.min = now;
data_vencimento.min = now;
