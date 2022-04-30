using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiHeroku.Data;
using ApiHeroku.Data.Model;

namespace ApiHeroku.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UserExampleController : ControllerBase
    {
        private readonly AppDbContext _context;

        public UserExampleController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/ToDoes
        [HttpGet]
        public async Task<ActionResult<IEnumerable<UserExample>>> GetToDos()
        {
            return await _context.UserInfosExample.ToListAsync();
        }

        // POST: api/ToDoes
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<UserExample>> PostToDo(UserExample userExample)
        {
            _context.UserInfosExample.Add(userExample);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetUserExample", new { id = userExample.UserId }, userExample);
        }
    }


}
