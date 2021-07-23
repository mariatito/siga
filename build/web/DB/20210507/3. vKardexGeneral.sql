create view vKardexGeneral as
select e.id_estudiante, p.id_persona,p.paterno, p.materno, p.nombres, concat(c.curso,' de ', c.ciclo) curso, c.id_curso, count(kd.su) no_notificado,cm.id_gestion
from kardexdetalles kd
inner join kardex k on k.idkardex =kd.idkardex
inner join curso_materias cm on  cm.id_curso_materia=k.id_curso_materia
inner join cursos c on c.id_curso=cm.id_curso
inner join estudiantes e on e.id_estudiante=kd.id_estudiante
inner join personas p on p.id_persona=e.id_persona
where kd.su=0
group by p.paterno, p.materno, p.nombres,c.curso, c.ciclo, c.id_curso,kd.su,cm.id_gestion,e.id_estudiante, p.id_persona
order by count(kd.su)desc,concat(p.paterno, p.materno, p.nombres)