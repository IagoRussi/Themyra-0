@echo off
cd /d "%~dp0"

echo Instalando pacotes...
npm install tailwindcss@latest @tailwindcss/cli@latest daisyui@latest
if %errorlevel% neq 0 (
    echo ERRO: Falha ao instalar pacotes npm.
    timeout /t 5
    exit /b 1
)

echo Fazendo build do Tailwind CSS...
npx @tailwindcss/cli -i src/main/resources/static/css/app.css -o src/main/resources/static/css/output.css
if %errorlevel% neq 0 (
    echo ERRO: Falha ao fazer a build do Tailwind CSS.
    timeout /t 5
    exit /b 1
)

echo Build concluida com sucesso! Pode usar o CSS gerado :)
timeout /t 5
