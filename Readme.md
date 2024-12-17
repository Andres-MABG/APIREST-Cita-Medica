## Api RestFull Cita Medica

### **1. Solicitar cita médica**
Permite a un usuario de tipo **paciente** solicitar una cita en un horario disponible utilizando el metodo POST con la **URL**: `/api/pacientes/citas` con el sistema de autenticacion ***Basic Auth*** de springboot security.  

**Body (JSON)**:
```json
{
   "pacienteId": 1,
   "fecha": "2024-12-15",
   "hora": "10:00",
   "especialidad": "Cardiología"
}
```

**Respuesta**:
```json
{
    "id": 1,
    "pacienteId": 1,
    "pacienteNombre": "Pablo Perez",
    "fecha": "2024-12-15",
    "hora": "10:00:00",
    "especialidad": "Cardiología",
    "estado": "pendiente",
    "mensajeError": null
}
```

### **2. Iniciar pago de cita**
Se inicia el proceso de pago en PayPal Sandbox para confirmar la cita utilizando el metodo POST con la **URL**: `/api/pagos/iniciar` con el sistema de autenticacion ***Basic Auth*** de springboot security.  

**Body (JSON)**:
```json
{
   "citaId": 1,
   "monto": 50.00
}
```

**Respuesta**:
```json
https: //www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=EC-03774501BG853863H
```
En el navegador se ingresa la url proporcionada por el servicio y se autoriza el pago correspondiente, que de ser exitoso redireccionará al segundo endpoint de pago con la **URL**: `/api/pagos/confirmar` para guardar la informacion correspondiente al mismo.

**Respuesta**:
```json
{
  "id": 1,
  "monto": 20,
  "fechaPago": "2024-12-15T14:32:39.702+00:00",
  "metodoPago": "PayPal",
  "cita": {
    "id": 1,
    "pacienteId": 1,
    "pacienteNombre": "Pablo Perez",
    "fecha": "2024-12-15 00:00:00.0",
    "hora": "10:00:00",
    "especialidad": "Cardiología",
    "estado": "pendiente",
    "mensajeError": null
  },
  "mensajeError": null
}
```
### **3. Confirmar Cita (Médico)**
Permite a un usuario de tipo **medico** confirmar una cita pagada asignandolo a la misma utilizando el metodo POST con la **URL**: `/api/medicos/citas/confirmar` con el sistema de autenticacion ***Basic Auth*** de springboot security.  

**Body (JSON)**:
```json
{
   "citaId": 1,
   "medicoId": 2
}
```

**Respuesta**:
```json
{
    "id": 1,
    "pacienteId": 1,
    "pacienteNombre": "Pablo Perez",
    "fecha": "2024-12-15 00:00:00.0",
    "hora": "10:00:00",
    "especialidad": "Cardiología",
    "estado": "confirmada",
    "mensajeError": null
}
```

### **4. Listar Citas del Día (Médico)**
Permite a un usuario de tipo **medico** ver todas las citas programadas para un día específico utilizando el metodo POST con la **URL**: `/api/medicos/citas` con el sistema de autenticacion ***Basic Auth*** de springboot security.  

**Respuesta**:
```json
[
    {
        "id": 1,
        "pacienteId": 1,
        "pacienteNombre": "Pablo Perez",
        "fecha": "2024-12-15 00:00:00.0",
        "hora": "10:00:00",
        "especialidad": "Cardiologia",
        "estado": "confirmada",
        "mensajeError": null
    },
    {
        "id": 2,
        "pacienteId": 1,
        "pacienteNombre": "Pablo Perez",
        "fecha": "2024-12-22 00:00:00.0",
        "hora": "11:00:00",
        "especialidad": "General",
        "estado": "confirmada",
        "mensajeError": null
    }
]
```
