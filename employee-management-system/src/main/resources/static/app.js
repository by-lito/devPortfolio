// ===============================
// CONFIGURACIÓN GENERAL
// ===============================

const API_URL = '/api/employees';

// 🔐 Basic Auth (usuario:password)
const AUTH_HEADER = 'Basic ' + btoa('admin:admin');

// Inicialización
document.addEventListener('DOMContentLoaded', () => {
    cargarEmpleados();
});


// ===============================
// 1️⃣ LECTURA (GET)
// ===============================

function cargarEmpleados() {

    fetch(API_URL, {
        method: 'GET',
        headers: {
            'Authorization': AUTH_HEADER,
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) throw new Error("Error HTTP: " + response.status);
        return response.json();
    })
    .then(data => {

        const tbody = document.getElementById('tablaEmpleados');
        tbody.innerHTML = '';

        data.forEach(emp => {

            // Protección contra nulos en relación @ManyToOne
            const jobId = emp.job ? emp.job.id : 'SIN PUESTO';

            tbody.innerHTML += `
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.firstName || ''} ${emp.lastName || ''}</td>
                    <td>${emp.email || ''}</td>
                    <td>
                        <span style="background:#eef; padding:2px 5px; border-radius:4px;">
                            ${jobId}
                        </span>
                    </td>
                    <td>${emp.salary ? emp.salary.toFixed(2) : '0.00'}</td>
                    <td style="text-align:center;">
                        <button class="btn-primary"
                            style="padding:5px 10px; font-size:0.8rem;"
                            onclick='cargarFormularioEdicion(${JSON.stringify(emp)})'>
                            ✏️ Editar
                        </button>

                        <button class="btn-secondary"
                            style="background-color:#e74c3c; padding:5px 10px; font-size:0.8rem;"
                            onclick="eliminarEmpleado(${emp.id})">
                            🗑️ Borrar
                        </button>
                    </td>
                </tr>
            `;
        });
    })
    .catch(err => console.error("Error al cargar:", err));
}



// ===============================
// 2️⃣ CREAR / ACTUALIZAR (POST / PUT)
// ===============================

function guardarEmpleado() {

    // 1. Recoger datos del formulario
    const id = document.getElementById('empId').value;
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const phoneNumber = document.getElementById('phoneNumber').value;
    const hireDate = document.getElementById('hireDate').value;
    const salary = document.getElementById('salary').value;
    const jobId = document.getElementById('jobId').value;

    // 2. Validaciones básicas
    if (!lastName || !email || !hireDate || !salary || !jobId) {
        alert("Por favor, rellena los campos obligatorios (*)");
        return;
    }

    // 3. Construcción del JSON
    // 🔴 IMPORTANTE: Estructura anidada para relación @ManyToOne
    const empleadoDTO = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phoneNumber: phoneNumber,
        hireDate: hireDate,
        salary: parseFloat(salary),
        job: {
            id: jobId
        }
    };

    // 4. Determinar método
    let url = API_URL;
    let method = 'POST';

    if (id) {
        url += `/${id}`;
        method = 'PUT';
    }

    // 5. Enviar petición
    fetch(url, {
        method: method,
        headers: {
            'Authorization': AUTH_HEADER,
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(empleadoDTO)
    })
    .then(async response => {

        if (response.ok) {
            alert(id
                ? "✅ Empleado actualizado correctamente"
                : "✅ Nuevo empleado creado"
            );

            limpiarFormulario();
            cargarEmpleados();
        } else {
            const errorText = await response.text();
            alert("❌ Error al guardar:\n" + errorText);
        }
    })
    .catch(err => console.error("Error de red:", err));
}



// ===============================
// 3️⃣ BORRADO (DELETE)
// ===============================

function eliminarEmpleado(id) {

    if (!confirm("¿Estás seguro de que quieres eliminar al empleado ID " + id + "?")) {
        return;
    }

    fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': AUTH_HEADER
        }
    })
    .then(response => {
        if (response.ok) {
            alert("🗑️ Empleado eliminado");
            cargarEmpleados();
        } else {
            alert("❌ No se pudo eliminar el empleado.");
        }
    })
    .catch(err => console.error("Error al borrar:", err));
}



// ===============================
// 4️⃣ FUNCIONES AUXILIARES
// ===============================

// Cargar datos en formulario (modo edición)
function cargarFormularioEdicion(empleado) {

    document.getElementById('formTitle').innerText =
        "✏️ Editando Empleado ID: " + empleado.id;

    document.getElementById('empId').value = empleado.id;
    document.getElementById('firstName').value = empleado.firstName || '';
    document.getElementById('lastName').value = empleado.lastName || '';
    document.getElementById('email').value = empleado.email || '';
    document.getElementById('phoneNumber').value = empleado.phoneNumber || '';
    document.getElementById('hireDate').value = empleado.hireDate || '';
    document.getElementById('salary').value = empleado.salary || 0;

    // Manejo seguro de relación @ManyToOne
    if (empleado.job) {
        document.getElementById('jobId').value = empleado.job.id;
    } else {
        document.getElementById('jobId').value = '';
    }

    window.scrollTo({ top: 0, behavior: 'smooth' });
}


// Reset formulario
function limpiarFormulario() {

    document.getElementById('formTitle').innerText = "➕ Nuevo Empleado";

    document.getElementById('empId').value = "";
    document.getElementById('firstName').value = "";
    document.getElementById('lastName').value = "";
    document.getElementById('email').value = "";
    document.getElementById('phoneNumber').value = "";
    document.getElementById('hireDate').value = "";
    document.getElementById('salary').value = "";
    document.getElementById('jobId').value = "IT_PROG"; // valor por defecto
}
