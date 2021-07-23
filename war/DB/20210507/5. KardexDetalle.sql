create view vKardexDetalle as
 SELECT 
   kd.idkardexdetalle,
	k.fecreg,
	kd.fsl,
	kd.a,
	kd.tnr,
	kd.aa,
	kd.otrasfaltas,
	kd.aspectospositivos,
	kd.observaciones,
	cm.id_gestion,
	kd.id_estudiante,
	cm.id_curso,
	concat(p.nombres,' ',p.paterno) as docente,
	m.materia
   FROM kardexdetalles kd
     JOIN kardex k ON k.idkardex = kd.idkardex
     JOIN curso_materias cm ON cm.id_curso_materia= k.id_curso_materia
     JOIN personal pe ON pe.id_personal = k.id_docente
    JOIN personas p ON p.id_persona = pe.id_persona
	join materias m on m.id_materia=cm.id_materia
  WHERE kd.su = 0 --and cm.id_gestion=2021 and kd.id_estudiante='CSC4267012-4e' and cm.id_curso='P8'