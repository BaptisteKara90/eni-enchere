
document.addEventListener("DOMContentLoaded", function () {
    let achatRadio = document.getElementById('achat');
    console.log(achatRadio);
    let venteRadio = document.getElementById('vente');
    console.log(venteRadio);
    let filter2AchatInputs = document.querySelectorAll('.achat');
    console.log(filter2AchatInputs);
    let filter2VenteInputs = document.querySelectorAll('.vente');
    console.log(filter2VenteInputs);

    function updateInputs() {
        if (achatRadio.checked) {
            filter2VenteInputs.forEach(function (input) {
                input.disabled = true;
            });
            filter2AchatInputs.forEach(function (input) {
                input.disabled = false;
            });
        } else if (venteRadio.checked) {
            filter2AchatInputs.forEach(function (input) {
                input.disabled = true;
            });
            filter2VenteInputs.forEach(function (input) {
                input.disabled = false;
            });
        }
    }

    achatRadio.addEventListener('change', updateInputs);
    venteRadio.addEventListener('change', updateInputs);

    updateInputs(); // Call once to set initial state
});