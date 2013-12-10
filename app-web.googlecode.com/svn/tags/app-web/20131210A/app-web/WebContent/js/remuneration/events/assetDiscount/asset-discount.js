var elements = [
	 			{name:'cParticipation',desc:'Participación',type:'Double',value:0},
	 			{name:'cExtraPay',desc:'Aguinaldo',type:'Double',value:0},
	 			{name:'cFamilyRetroactive',desc:'Familiar Retroactivo',type:'Double',value:0},
	 			{name:'cFeeding',desc:'Alimentación',type:'Double',value:0},
	 			{name:'cMobilization',desc:'Movilización',type:'Double',value:0},
	 			{name:'cBounty',desc:'Viaticos',type:'Double',value:0},
	 			{name:'cMonthNotification',desc:'Mes de aviso',type:'Double',value:0},
	 			{name:'cIAS',desc:'Indemnización años de servicio',type:'Double',value:0},
	 			{name:'cLoanEnterprise',desc:'Prestamo de empresa',type:'Double',value:0},
	 			{name:'cLoanCompensationFund',desc:'Prestamo Caja compensación',type:'Double',value:0},
	 			{name:'cSavingCompensationFund',desc:'Ahorro Caja compesación',type:'Double',value:0},
	 			{name:'cJudicialRetention',desc:'Retención Judicial',type:'Double',value:0}
	 			];
function sendForm(){
//	alert(JSON.stringify(elements));
	severalValidationsAndSubmit(elements, "frm");
}