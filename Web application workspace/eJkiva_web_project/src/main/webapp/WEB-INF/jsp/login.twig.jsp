<!DOCTYPE html>
  
<h2>Enter information</h2> 
<!--<form:form method="post" action="login.html">-->    
{# comment #}
{% if form is defined %}   

	<div class="form-group">
	{{ form_start(form) }}
    

 	    {{ form_label(form.uname) }}
  		{{ form_widget(form.uname) }}
  		{{ form_errors(form.uname) }}
    
  		{{ form_label(form.password) }}
   		{{ form_widget(form.password) }}
   		{{ form_errors(form.password) }}
		<br/>  		
   	 	{{ form_widget(form.login, { 'attr': {'class': 'btn btn-primary'} }) }}
	{{ form_end(form) }}    		
	</div>
	
{% endif %}	       
<!--</form:form>-->   
